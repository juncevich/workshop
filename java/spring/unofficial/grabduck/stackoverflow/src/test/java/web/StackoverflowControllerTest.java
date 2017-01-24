package web;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.common.collect.ImmutableList;

import de.grabduck.model.StackoverflowWebSite;
import de.grabduck.service.StackoverflowService;
import de.grabduck.web.StackoverflowController;

/**
 * Created by alex on 24.01.17.
 */
@RunWith(MockitoJUnitRunner.class) public class StackoverflowControllerTest {

    @InjectMocks StackoverflowController sut;

    @Mock private StackoverflowService stackoverflowService;

    @Test public void testGetListOfProviders() throws Exception {
        //prepare
        when(stackoverflowService.findAll()).thenReturn(ImmutableList.of());
        //testing
        List<StackoverflowWebSite> listOfProviders = sut.getListOfProviders();
        //validate
        verify(stackoverflowService).findAll();
    }
}
