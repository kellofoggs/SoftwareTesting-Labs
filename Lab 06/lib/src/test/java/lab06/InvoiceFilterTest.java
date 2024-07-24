package lab06;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.*;
import java.util.stream.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;

class InvoiceFilterTest {
    private InvoiceFilter invoiceFilter;
    private IssuedInvoices issuedInvoices;

    @Test
    void allHighValueInvoices() {
        // every invoice in the list should be high-value
        IssuedInvoices issuedInvoicesMock = mock(IssuedInvoices.class, withSettings().verboseLogging());
        invoiceFilter = new InvoiceFilter(issuedInvoicesMock);
        when(issuedInvoicesMock.all()).thenReturn(List.of(new Invoice(100),new Invoice(105), new Invoice(500), new Invoice (300)));
        assertThat(invoiceFilter.highValueInvoices()).containsExactly(new Invoice(100),new Invoice(105), new Invoice(500), new Invoice (300));
        verify(issuedInvoicesMock).all();
        verifyNoMoreInteractions(issuedInvoicesMock);

    }

    @Test
    void allLowValueInvoices() {
        IssuedInvoices issuedInvoicesMock = mock(IssuedInvoices.class, withSettings().verboseLogging());
        invoiceFilter = new InvoiceFilter(issuedInvoicesMock);
        when(issuedInvoicesMock.all()).thenReturn(List.of(new Invoice(43), new Invoice(99)));
        assertThat(invoiceFilter.lowValueInvoices()).containsExactly(new Invoice(43), new Invoice(99));
        verify(issuedInvoicesMock).all();
        verifyNoMoreInteractions(issuedInvoicesMock);

    }

    @Test
    void someLowValueInvoices() {
        // Some low value invoices, some high
        IssuedInvoices issuedInvoicesMock = mock(IssuedInvoices.class, withSettings().verboseLogging());
        invoiceFilter = new InvoiceFilter(issuedInvoicesMock);
        when(issuedInvoicesMock.all()).thenReturn(List.of(new Invoice(43), new Invoice(99), new Invoice(105), new Invoice(500), new Invoice (300)));
        assertThat(invoiceFilter.lowValueInvoices()).containsExactly(new Invoice(43), new Invoice(99));

        assertThat(invoiceFilter.highValueInvoices()).containsExactly(new Invoice(105), new Invoice(500), new Invoice (300));

        verify(issuedInvoicesMock, times(2)).all();
        verifyNoMoreInteractions(issuedInvoicesMock);
    }

}
