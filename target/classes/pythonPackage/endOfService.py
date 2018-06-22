#!/usr/bin/python
# -*- coding: utf-8 -*-

from py4j.java_gateway import JavaGateway

gateway = JavaGateway()
entry = gateway.entry_point
smo_parent = gateway.entry_point.getSmoParent()
zgloszenie = gateway.entry_point.getZgloszenie()
smo_parent.setWolne(True)

print ("Python "+str(entry.simTime())+" - "+str(entry.simHour())+" - "+str(entry.simMinute())+" - "+str(entry.simSecond())+" - "+str(entry.simMillisecond())+": SMO- Koniec obsługi zgl. nr: " + str(zgloszenie.getTenNr()))

if smo_parent.liczbaZgl() > 0:
    smo_parent.setRozpocznijObsluge(smo_parent)
    #return "new RozpocznijObsluge(smoParentc)"