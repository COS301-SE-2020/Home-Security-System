#!/usr/bin/env node
var amqp = require('amqplib/callback_api');
var msgsArr = [];
var localArr = [];
var emailArr = [];

amqp.connect('amqp://localhost', function (error0, connection) {
  if (error0) {
    throw error0;
  }
  connection.createChannel(function (error1, channel) {
    if (error1) {
      throw error1;
    }

    var queue = 'notifications';

    channel.assertQueue(queue, {
      durable: false
    });

    console.log(" [*] Waiting for messages in %s. To exit press CTRL+C", queue);

    channel.consume(queue, function (msg) {

      msgsArr.push(msg.content.toString());

      if (msg.content.toString().substr(0, 1) === "L")
        localArr.push(msg.content.toString());

      else
        emailArr.push(msg.content.toString());

      const fs = require('fs');

      let msgs = {
        message: msgsArr,
        local: localArr,
        email: emailArr
      };
      let data = JSON.stringify(msgs);
      fs.writeFileSync('messages.json', data);

      console.log(" [x] Received %s", msg.content.toString());
    }, {
      noAck: true
    });
  });
});


