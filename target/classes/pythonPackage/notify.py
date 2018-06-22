#!/usr/bin/python
# -*- coding: utf-8 -*-

from py4j.java_gateway import JavaGateway

gateway = JavaGateway()
entry = gateway.entry_point
parent = gateway.entry_point.getSimObj()
generator = gateway.entry_point.getGenerator()
parent_smo = parent.getSmo()
time = entry.simTime()
zgl = entry.newZgloszenie(time, parent_smo)

parent_smo.dodaj(zgl)

print ("Python Notify "+str(entry.simTime())+" - "+str(entry.simHour())+" - "+str(entry.simMinute())+" - "+str(entry.simSecond())+" - "+str(entry.simMillisecond())+": Otoczenie- Dodano nowe zgl. nr: " + str(zgl.getTenNr()));

if (parent_smo.liczbaZgl()==1 and parent_smo.isWolne()):
    parent_smo.setRozpocznijObsluge(parent_smo)

odstep = generator.normal(5.0, 1.0)
entry.setRepetitionPeriod(odstep)