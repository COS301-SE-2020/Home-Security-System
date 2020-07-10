#!/usr/bin/env node


  var amqp = require('amqplib/callback_api');
  var msgsArr = [];

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
          const fs = require('fs');

          let msgs = {
            message: msgsArr
          };
          let data = JSON.stringify(msgs);
          fs.writeFileSync('messages.json', data);

          console.log(" [x] Received %s", msg.content.toString());
      }, {
        noAck: true
      });
    });
  });


