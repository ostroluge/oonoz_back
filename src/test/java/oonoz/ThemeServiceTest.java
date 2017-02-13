package oonoz;

import java.text.ParseException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import oonoz.domain.Theme;
import oonoz.exception.ThemeAlreadyExistException;
import oonoz.exception.WrongInformationException;
import oonoz.manager.impl.ThemeManagerImpl;
import oonoz.service.ThemeService;

/**
 * The Class ThemeServiceTest.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=OonozApplication.class)
public class ThemeServiceTest {

	/** The theme service. */
	@Autowired
	@InjectMocks
	private ThemeService themeService;
	
	/** The theme manager. */
	@Mock
	private ThemeManagerImpl themeManager;

	/**
	 * Inits the.
	 *
	 * @throws ParseException the parse exception
	 */
	@Before
	public void init() throws ParseException {
		MockitoAnnotations.initMocks(this);
	}

	/**
	 * Post theme with wrong label.
	 *
	 * @throws WrongInformationException the wrong information exception
	 * @throws ThemeAlreadyExistException the theme already exist exception
	 */
	@Test(expected = WrongInformationException.class)
	public void postThemeWithWrongLabel() throws WrongInformationException, ThemeAlreadyExistException {
		//[-- INITIALISATION --]
		Theme theme = new Theme();
		theme.setDescription("Ceci est une description");
		theme.setLabel("Ceci est un label beaucoup trop long");
		
		//[-- APPEL DU SERVICE --]
		themeService.postTheme(theme);
		
		//[-- VERIFICATION --]
		//Must throw WrongInformationException
	}

	/**
	 * Post theme with wrong description.
	 *
	 * @throws WrongInformationException the wrong information exception
	 * @throws ThemeAlreadyExistException the theme already exist exception
	 */
	@Test(expected = WrongInformationException.class)
	public void postThemeWithWrongDescription() throws WrongInformationException, ThemeAlreadyExistException {
		//[-- INITIALISATION --]
		Theme theme = new Theme();
		theme.setDescription("bad");
		theme.setLabel("Correct Label");
		
		//[-- APPEL DU SERVICE --]
		themeService.postTheme(theme);
		
		//[-- VERIFICATION --]
		//Must throw WrongInformationException
	}

	/**
	 * Post theme.
	 *
	 * @throws WrongInformationException the wrong information exception
	 * @throws ThemeAlreadyExistException the theme already exist exception
	 */
	@Test
	public void postTheme() throws WrongInformationException, ThemeAlreadyExistException {
		//[-- INITIALISATION --]
		Theme theme = new Theme();
		theme.setLabel("Theme");
		theme.setDescription("description");
		
		//[-- APPEL DU SERVICE --]
		themeService.postTheme(theme);
		
		//[-- VERIFICATION --]
		final ArgumentCaptor<Theme> argument = ArgumentCaptor.forClass(Theme.class);
		Mockito.verify(this.themeManager, Mockito.times(1)).create(argument.capture());
	}
	
//	@Test(expected = ThemeAlreadyExistException.class)
//	public void postThemeThatAlreadyExists() throws ThemeAlreadyExistException, WrongInformationException {
		//[-- INITIALISATION --]
//		Theme theme1 = new Theme();
//		theme1.setLabel("Theme");
//		theme1.setDescription("theme 1 description");
		
//		Theme theme2 = new Theme();
//		theme2.setLabel("Theme");
//		theme2.setDescription("theme 2 description");
		
		//[-- APPEL DU SERVICE --]
//		themeService.postTheme(theme2);
		
		//[-- VERIFICATION --]
		//Must throw ThemeAlreadyExistException
//	}
}
