#!/usr/bin/python
# -*- coding: utf-8 -*-

from py4j.java_gateway import JavaGateway

gateway = JavaGateway()
entry = gateway.entry_point
smoParent = gateway.entry_point.getSmoParent()
generator = gateway.entry_point.getGenerator()


if (smoParent.liczbaZgl() > 0):

    smoParent.setWolne(False)
    zgl = smoParent.usun()
    # Przerwanie niecierpliwosci
    zgl.getKoniecNiecierpliwosci().interrupt()
    # Wygeneruj czas obsługi
    czasObslugi = generator.normal(9.0, 1.0)
    print (str(entry.simTime())+" - "+str(entry.simHour())+" - "+str(entry.simMinute())+" - "+str(entry.simSecond())+" - "+str(entry.simMillisecond())+": SMO- Początek obsługi zgl. nr: " + str(zgl.getTenNr()))

    # Zaplanuj koniec obsługi
    smoParent.setZakonczObsluge(smoParent, czasObslugi, zgl)