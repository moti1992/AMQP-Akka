package com.amqp.akka

import akka.actor.Actor
import com.rabbitmq.client.Channel

//AMQP sender is an actor used to produce the messages for consume
class AMQP_Publisher(channel:Channel,queue:String) extends Actor{
  def receive= {
    case message:String => channel.basicPublish(Defaults.EXCHANGE,queue,null,message.getBytes())
  }
}
