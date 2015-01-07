package com.amqp.akka

import akka.actor.Actor
import com.rabbitmq.client.{Channel, QueueingConsumer}

import scala.concurrent.ExecutionContext

//AMQP listener is an actor used to consume the messages from sender
class AMQP_Consumer(channel:Channel,queue:String) extends Actor{
  override def preStart = self ! "init"

  def receive = {
    case _ => startReceiving
  }

  def startReceiving = {
    val consumer:QueueingConsumer = new QueueingConsumer(channel)
    channel.basicConsume(queue, true, consumer)
    implicit val ec:ExecutionContext = ExecutionContext.Implicits.global
    while (true) {
      val delivery: QueueingConsumer.Delivery = consumer.nextDelivery()
      val msg: String = new String(delivery.getBody)
      println(sender().path + " got " + msg)
    }
  }
}
