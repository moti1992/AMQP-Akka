package com.amqp.akka

import akka.actor.{Props, ActorSystem}

object Publisher extends App{
  val system = ActorSystem("PublisherSystem")
  val publisherChannel = AMQP.getConnection.createChannel()
  //val publishQueue = publisherChannel.queueDeclare.getQueue
  publisherChannel.queueBind(Defaults.QUEUE_SENDER,Defaults.EXCHANGE,"")
  val publish = system.actorOf(Props(new AMQP_Publisher(publisherChannel,Defaults.QUEUE_SENDER)),"publisher")
  //TODO: use futures to send messages concurrently
  (1 to Defaults.NUMBER_OF_MESSAGES) foreach { i => publish ! (" msg " + i)}
}
