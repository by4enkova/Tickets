package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Ticket;
import ru.netology.repository.TicketRepository;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class TicketManagerTest {
    private TicketRepository repository = new TicketRepository();
    private TicketManager manager = new TicketManager(repository);

    private Ticket first = new Ticket(1, 2000, "SVO", "LED", 90);
    private Ticket second = new Ticket(2, 3000, "SVO", "KZN", 95);
    private Ticket third = new Ticket(3, 2200, "VKO", "KZN", 95);
    private Ticket fourth = new Ticket(4, 6000, "SVO", "LED", 180);
    private Ticket fifth = new Ticket(5, 4000, "SVO", "LED", 120);
    private Ticket sixth = new Ticket(6, 5000, "SVO", "KZN", 90);
    private Ticket seventh = new Ticket(7, 4000, "VKO", "KZN", 90);
    private Ticket eighth = new Ticket(8, 7000, "SVO", "LED", 180);

    @BeforeEach
    public void addTicket() {
        repository.save(first);
        repository.save(second);
        repository.save(third);
        repository.save(fourth);
        repository.save(fifth);
        repository.save(sixth);
        repository.save(seventh);
        repository.save(eighth);
    }

    @Test
    void shouldFindSvoToLedTickets() {
        Ticket[] expected = new Ticket[]{first, fifth, fourth, eighth};

        Ticket[] actual = manager.findAllTickets("SVO", "LED");

        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldFindSvoToKznTickets() {
        Ticket[] expected = new Ticket[]{second, sixth};

        Ticket[] actual = manager.findAllTickets("SVO", "KZN");

        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldNotFindAndSort() {
        Ticket[] expected = new Ticket[]{};
        Ticket[] actual = manager.findAllTickets("PES", "SVO");

        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldSorTicketsByPrice() {
        Ticket[] expected = new Ticket[]{first, third, second, fifth, seventh, sixth, fourth, eighth};
        Ticket[] actual = repository.findAll();
        Arrays.sort(actual);

        assertArrayEquals(expected, actual);
    }


}