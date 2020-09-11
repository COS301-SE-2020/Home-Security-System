let Peer = require('simple-peer')
let socket = io()
const selfVideo = document.getElementById('selfCam') //Own video (must change)
let client = {}

//get stream
navigator.mediaDevices.getUserMedia({video: true, audio: true})//Change width and height here (in video tag)
  .then(stream => {
    socket.emit('NewClient')
    selfVideo.srcObject = stream
    selfVideo.play()

    //used to initialize a peer
    function InitPeer(type) {
      let peer = new Peer({initiator: (type === 'init') ? true : false, stream: stream, trickle: false})
      peer.on('stream', function (stream) {
        CreateVideo(stream)
      })
      //This isn't working in chrome; works perfectly in firefox.
      // peer.on('close', function () {
      //     document.getElementById("peerVideo").remove();
      //     peer.destroy()
      // })
      peer.on('data', function (data) {
        let decodedData = new TextDecoder('utf-8').decode(data)
        let peervideo = document.querySelector('#peerVideo')
        peervideo.style.filter = decodedData
      })
      return peer
    }

    //for peer of type init
    function MakePeer() {
      client.gotAnswer = false
      let peer = InitPeer('init')
      peer.on('signal', function (data) {
        if (!client.gotAnswer) {
          socket.emit('Offer', data)
        }
      })
      client.peer = peer
    }

    //for peer of type not init. Basically another user.
    function FrontAnswer(offer) {
      let peer = InitPeer('notInit')
      peer.on('signal', (data) => {
        socket.emit('Answer', data)
      })
      peer.signal(offer)
      client.peer = peer
    }

    function SignalAnswer(answer) {
      client.gotAnswer = true
      let peer = client.peer
      peer.signal(answer)
    }

    function CreateVideo(stream) {
      let newVideo = document.createElement('video')
      newVideo.id = 'peerVideo'
      newVideo.srcObject = stream
      newVideo.setAttribute('class', 'embed-responsive-item')
      document.querySelector('#peerDiv').appendChild(newVideo)
      newVideo.play()

      newVideo.addEventListener('click', () => {
        if (newVideo.volume !== 0)
          newVideo.volume = 0
        else
          newVideo.volume = 1
      })

    }

    function SessionActive() {
      document.write('Session Active. Please come back later')
    }

    function RemovePeer() {
      document.getElementById("peerVideo").remove();
      document.getElementById("muteText").remove();
      if (client.peer) {
        client.peer.destroy()
      }
    }

    //Events and responses
    socket.on('BackOffer', FrontAnswer)
    socket.on('BackAnswer', SignalAnswer)
    socket.on('SessionActive', SessionActive)
    socket.on('CreatePeer', MakePeer)
    socket.on('Disconnect', RemovePeer)

  })
  .catch(err => document.write(err))
