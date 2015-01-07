package com.amqp.akka

object Defaults {
  val PORT = 5672
  val HOST = "127.0.0.1"
  val QUEUE_SENDER = "SendQueue"
  val QUEUE_RECEIVER = "ReceiveQueue"
  val NUMBER_OF_MESSAGES = 1000
  val NUMBER_OF_WORKERS = 10
  val EXCHANGE = "amq.fanout"
}
