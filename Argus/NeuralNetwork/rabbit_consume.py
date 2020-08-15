import json
import pika as pi

path_features = './models/'
rabbit_host = 'localhost'

rabbit_conn = pi.BlockingConnection(pi.ConnectionParameters(rabbit_host))
message_channel = rabbit_conn.channel()


def feature_update(ch, method, props, body):
    message = json.loads(body)


message_channel.basic_consume(queue='',
                              auto_ack=True,
                              on_message_callback=feature_update)
message_channel.start_consuming()
