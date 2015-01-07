package com.amqp.akka

import akka.actor.{ActorRef, ActorSystem, Props}
import akka.routing.ConsistentHashingPool
import akka.routing.ConsistentHashingRouter.{ConsistentHashMapping, ConsistentHashable}

object Consumer extends App{
  val system = ActorSystem("ConsumerSystem")
  val consumerChannel = AMQP.getConnection.createChannel()
  //TODO: and also make this run concurrently
  //val consumeQueue = consumerChannel.queueDeclare.getQueue
  consumerChannel.queueBind(Defaults.QUEUE_RECEIVER,Defaults.EXCHANGE,"")

  case class Add(key:String,value:String)
  case class Remove(key:String)
  case class getKey(key:String) extends ConsistentHashable{
    override def consistentHashKey: Any = key
  }

  def hashMapping:ConsistentHashMapping={
    case Add(key,_) => key
    case getKey(key) => key
  }

  val router1:ActorRef = system.actorOf(ConsistentHashingPool(10,hashMapping=hashMapping).props(Props(new AMQP_Consumer(consumerChannel,Defaults.QUEUE_RECEIVER))),"r1")
  //val router2:ActorRef = system.actorOf(ConsistentHashingGroup(paths2).withHashMapper(hashMapper).props(),"r2")
}
