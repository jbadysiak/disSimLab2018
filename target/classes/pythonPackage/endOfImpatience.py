#!/usr/bin/python
# -*- coding: utf-8 -*-

from py4j.java_gateway import JavaGateway

gateway = JavaGateway()
entry = gateway.entry_point
parent = gateway.entry_point.getParent()
parent_smo = gateway.entry_point.getParent().getSmo()

print ("Python endOfImpatience" + str(entry.simTime())+" - "+str(entry.simHour())+" - "+str(entry.simMinute())+" - "+str(entry.simSecond())+" - "+str(entry.simMillisecond())+": Koniec niecierpliwości zgl. nr: " + str(parent.getTenNr()))

if (parent_smo.usunWskazany(parent)):
    print ("Python endOfImpatience" + str(entry.simTime())+" - "+str(entry.simHour())+" - "+str(entry.simMinute())+" - "+str(entry.simSecond())+" - "+str(entry.simMillisecond())+": Usunięto z kolejki zgl. nr: " + str(parent.getTenNr()))
else:
    print ("Python endOfImpatience" + str(entry.simTime())+" - "+str(entry.simHour())+" - "+str(entry.simMinute())+" - "+str(entry.simSecond())+" - "+str(entry.simMillisecond())+": Problem z usunięciem z kolejki zgl. nr: " + str(parent.getTenNr()))
