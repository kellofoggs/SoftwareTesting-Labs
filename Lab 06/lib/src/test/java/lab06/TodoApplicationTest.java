
package lab06;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

import org.mockito.Mockito;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

class TodoApplicationTest {

    private TodoApplication todoApp;
    private PersonService personServiceMock;
    private TodoService todoServiceMock;

    private final String userName = "SomeUser";
    private final Long userID = 1L;
    private final List<String> todos = List.of("Wake up", "Test the code", "Celebrate the victory!");

    //Would it be better to create the mocks in a before statement?
    @Test
    void addTodo() {
        // Ensure that it's possible to add a todo to the app, and that the correct methods are called
        todoServiceMock = mock(TodoService.class, withSettings().verboseLogging());
         personServiceMock = mock(PersonService.class, withSettings().verboseLogging());
        TodoApplication todoApp = new TodoApplication(todoServiceMock, personServiceMock);
        when(personServiceMock.findUsernameById(userID)).thenReturn(userName);
        for (String task: todos){
         //Should this test also check if the to do appears with retrieve todos?
            assertThat(todoApp.addTodo(userID, task));

        }
        verify(personServiceMock, times(3)).findUsernameById((any()));
        verify(todoServiceMock, times(3)).addTodo(any(),any());

    }

    @Test
    void retrieveTodos() {
        // add multiple todos to the app, and retrieve a strict subset of them using a substring.
        todoServiceMock = mock(TodoService.class, withSettings().verboseLogging());
        personServiceMock = mock(PersonService.class, withSettings().verboseLogging());
        todoApp = new TodoApplication(todoServiceMock, personServiceMock);

        //Assign userName to user var in mock retrieveTodos
        when(personServiceMock.findUsernameById(userID)).thenReturn(userName);

        //Assign list of tasks to 'allTodos' var in mock retrieveTodos
        when(todoServiceMock.retrieveTodos(userName)).thenReturn(todos);

        //Checks that phrases with "the" in to do list can be retrieved
        assertThat(todoApp.retrieveTodos(userID, "the" ).containsAll(List.of("Test the code", "Celebrate the victory!" ) ) );
        verify(personServiceMock, times(1)).findUsernameById(any());
        verify(todoServiceMock, times(1)).retrieveTodos(any());


    }

    @Test
    void completeAllWithNoTodos() {
       // confirm that the appropriate behaviour occurs when there are no todos being tracked by the app
         todoServiceMock = mock(TodoService.class, withSettings().verboseLogging());
         personServiceMock = mock(PersonService.class, withSettings().verboseLogging());
        todoApp = new TodoApplication(todoServiceMock, personServiceMock);


        when (personServiceMock.findUsernameById(userID)).thenReturn(userName);

        //Empty to do list
        when(todoServiceMock.retrieveTodos(userName)).thenReturn(List.of());

        todoApp.completeAllTodos(userID);
        assertThat(todoApp.retrieveTodos(userID, " ").size() == 0);

        verify(todoServiceMock, times(0)).completeTodo(any());
    }

    @Test
    void completeAllWithThreeTodos() {
        // confirm that the appropriate behaviour occurs when there are three todos being tracked by the app
        todoServiceMock = mock(TodoService.class, withSettings().verboseLogging());
        personServiceMock = mock(PersonService.class, withSettings().verboseLogging());
        todoApp = new TodoApplication(todoServiceMock, personServiceMock);
        when (personServiceMock.findUsernameById(userID)).thenReturn(userName);
        when (todoServiceMock.retrieveTodos(userName)).thenReturn(todos);


        assertThat(todoApp.retrieveTodos(userID, " ").size() == 3);
        todoApp.completeAllTodos(userID);
        verify(todoServiceMock, times(3)).completeTodo(any());
    }
}