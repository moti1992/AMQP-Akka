package com.amqp.akka

import com.rabbitmq.client.{ConnectionFactory, Connection}

object AMQP {
  val connection:Connection = null
  //creates a connection factory and return the connection
  def getConnection():Connection = {
    connection match {
      case null =>
        val factory: ConnectionFactory = new ConnectionFactory
        factory.setHost(Defaults.HOST)
        factory.setPort(Defaults.PORT)
        factory.setAutomaticRecoveryEnabled(true)
        factory.newConnection()
      case _ => connection
    }
  }
}
