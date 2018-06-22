package smo.newpackage;

import dissimlab.simcore.SimControlException;
import smo.Zgloszenie;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MyQueue<T extends Zgloszenie> {

    private QueueType queueType;
    private long aLong;
    private boolean priority;
    private List<T> zgloszenia = new ArrayList<>();

    public MyQueue(QueueType queueType1, long l, boolean prioritet) {
        this.queueType = queueType1;
        this.aLong = l;
        this.priority = prioritet;
    }

    public T get() {
        if (zgloszenia.isEmpty()) {
            return null;
        }
        T object = null;
        if (queueType.equals(QueueType.FIFO)) {
            object = fifo();
        } else if (queueType.equals(QueueType.LIFO)) {
            object = lifo();
        }
        zgloszenia.remove(object);
        return object;
    }

    private T lifo() {
        if (priority) {
            List<T> maxPrioObiekty = getMaxPrioObiekty();
            return maxPrioObiekty.get(maxPrioObiekty.size() - 1);
        }
        return zgloszenia.get(zgloszenia.size() - 1);
    }

    private T fifo() {
        if (priority) {
            List<T> maxPrioObiekty = getMaxPrioObiekty();
            return maxPrioObiekty.get(0);
        }
        return zgloszenia.get(0);
    }

    private List<T> getMaxPrioObiekty() {
        int maxPrio = zgloszenia.stream().map(T::getPriorytet).max(Integer::compareTo).get();
        return zgloszenia.stream().filter(o -> o.getPriorytet() == maxPrio).collect(Collectors.toList());
    }

    public void add(T obiekt) throws QueueException, SimControlException {
        if (aLong <= 0 || zgloszenia.size() < aLong) {
            zgloszenia.add(obiekt);
            obiekt.uruchomNiecierpliwienie();
        } else {
            throw new QueueException();
        }
    }

    public int getSize() {
        return zgloszenia.size();
    }

    public boolean usunWskazany(T obiekt) {
        return zgloszenia.remove(obiekt);
    }
}
