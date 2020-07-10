#!/usr/bin/env node

    var amqp = require('amqplib/callback_api');

//connection to the rabbitmq server
    amqp.connect('amqp://localhost', function(error0, connection) {
        if (error0) {
            throw error0;
        }
        connection.createChannel(function(error1, channel) {
            if (error1) {
                throw error1;
            }

            //queue name for the RabbitMQ
            var queue = 'notifications';
            var msg = 'Local message sent';

            channel.assertQueue(queue, {
                durable: false
            });
            channel.sendToQueue(queue, Buffer.from(msg));

            //confirm that message is sent
            console.log(" [x] Sent %s", msg);
        });
        //close connection after it is sent
        setTimeout(function() {
            connection.close();
            process.exit(0);
        }, 500);
    });


