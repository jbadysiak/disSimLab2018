#!/usr/bin/python
# -*- coding: utf-8 -*-

from py4j.java_gateway import JavaGateway

gateway = JavaGateway()
entry = gateway.entry_point
parent = gateway.entry_point.getParent()
generator = gateway.entry_point.getGenerator()

print (str(entry.simTime())+" - "+str(entry.simHour())+" - "+str(entry.simMinute())+" - "+str(entry.simSecond())+" - "+str(entry.simMillisecond())+": Początek niecierpliwości zgl. nr: " + str(parent.getTenNr()))

odstep = generator.normal(15.0, 1.0)
parent.setKoniecNiecierpliwosci(parent, odstep)
