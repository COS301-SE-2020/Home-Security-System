#!/usr/bin/env node
/*
            channel.consume(queue, function(msg) {
                console.log(" [x] Received %s", msg.content.toString());
            const fs = require('fs');

            let student = {
                local: ["1"],
                email: ["2"]
            };
            let data = JSON.stringify(student);
            fs.writeFileSync('messages.json', data);
            */
    var amqp = require('amqplib/callback_api');

    amqp.connect('amqp://localhost', function(error0, connection) {
        if (error0) {
            throw error0;
        }
        connection.createChannel(function(error1, channel) {
            if (error1) {
                throw error1;
            }

            var queue = 'notifications';

            channel.assertQueue(queue, {
                durable: false
            });

            console.log(" [*] Waiting for messages in %s. To exit press CTRL+C", queue);

            channel.consume(queue, function(msg) {
                console.log(" [x] Received %s", msg.content.toString());
            }, {
                noAck: true
            });
        });
    });


