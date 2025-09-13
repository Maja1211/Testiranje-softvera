
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.*;

public class RegistracijaTest {

    private WebDriver driver;
    private String baseUrl = "http://localhost/projekat5/IP_projekat/index.php";

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.gecko.driver", "C:\\Users\\Win 11\\Desktop\\DZ1\\geckodriver-v0.34.0-win64\\geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
    }

    @BeforeMethod
    public void goToHomePage() {
        driver.get(baseUrl);
    }

    @Test
    public void test01RegistracijaIspravna() {
        driver.findElement(By.linkText("Postani korisnik")).click();
        Assert.assertTrue(driver.getCurrentUrl().contains("registracija.php"));

        driver.findElement(By.cssSelector("input[type='radio'][value='student']")).click();
        driver.findElement(By.xpath("//input[@type='submit' and @value='Zapocni registraciju']")).click();
        Assert.assertTrue(driver.getCurrentUrl().contains("reg_student.php"));

        driver.findElement(By.name("k_ime")).sendKeys("maja");
        driver.findElement(By.name("lozinka")).sendKeys("Maja123*");
        driver.findElement(By.name("lozinka_potvrdi")).sendKeys("Maja123*");
        driver.findElement(By.name("ime")).sendKeys("Maja");
        driver.findElement(By.name("prezime")).sendKeys("Špirto");
        driver.findElement(By.name("telefon")).sendKeys("0645694076");
        driver.findElement(By.name("eposta")).sendKeys("maja@gmail.com");
        driver.findElement(By.name("god_stud")).sendKeys("3");

        Select diplomaSelect = new Select(driver.findElement(By.name("diploma")));
        diplomaSelect.selectByValue("0");

        WebElement fileInput = driver.findElement(By.name("slika"));
        fileInput.sendKeys("C:\\Users\\Win 11\\Desktop\\DZ1\\slike\\slika4.jpg");

        driver.findElement(By.xpath("//input[@type='submit' and @value='Registruj se']")).click();

        try {

            WebElement poruka = driver.findElement(By.xpath("//center"));
            String tekst = poruka.getText().trim();

            Assert.assertTrue(tekst.contains("Uspesno ste se registrovali!"));
            WebElement link = driver.findElement(By.xpath("//center/a"));
            Assert.assertEquals(link.getText().trim(), "Nazad na prijavu.");

            System.out.println("TEST01 REGISTRACIJA USPESNA - PASS");
        } catch (Throwable e) {
            System.out.println("TEST01 REGISTRACIJA USPESNA - FAILED: " + e.getMessage());
        }
    }

    @Test
    public void test02RegistracijaLozinkaPrekratka() {

        driver.findElement(By.linkText("Postani korisnik")).click();
        driver.findElement(By.cssSelector("input[type='radio'][value='student']")).click();
        driver.findElement(By.xpath("//input[@type='submit' and @value='Zapocni registraciju']")).click();

        driver.findElement(By.name("k_ime")).sendKeys("ana123");
        driver.findElement(By.name("lozinka")).sendKeys("aNac1*");
        driver.findElement(By.name("lozinka_potvrdi")).sendKeys("aNac1*");
        driver.findElement(By.name("ime")).sendKeys("Ana");
        driver.findElement(By.name("prezime")).sendKeys("Anic");
        driver.findElement(By.name("telefon")).sendKeys("065/048-242");
        driver.findElement(By.name("eposta")).sendKeys("anaanic@gmail.com");
        driver.findElement(By.name("god_stud")).sendKeys("4");
        new Select(driver.findElement(By.name("diploma"))).selectByValue("0");
        driver.findElement(By.name("slika"))
                .sendKeys("C:\\Users\\Win 11\\Desktop\\DZ1\\slike\\slika4.jpg");

        driver.findElement(By.xpath("//input[@type='submit' and @value='Registruj se']")).click();

        try {
            String text = driver.switchTo().alert().getText();
            driver.switchTo().alert().accept();

            Assert.assertTrue(text.contains("Lozinka mora imati 8–12 karaktera"));
            System.out.println("TEST02 LOZINKA PREKRATKA - PASS");

        } catch (Throwable e) {
            System.out.println("TEST02 LOZINKA PREKRATKA - FAILED");
            Assert.fail("TEST02 LOZINKA PREKRATKA - FAILED");
        }
    }

    @Test
    public void test03RegistracijaLozinkaPreduga() {

        driver.findElement(By.linkText("Postani korisnik")).click();
        driver.findElement(By.cssSelector("input[type='radio'][value='student']")).click();
        driver.findElement(By.xpath("//input[@type='submit' and @value='Zapocni registraciju']")).click();

        driver.findElement(By.name("k_ime")).sendKeys("nikola12");
        driver.findElement(By.name("lozinka")).sendKeys("Nikola1234567*");
        driver.findElement(By.name("lozinka_potvrdi")).sendKeys("Nikola1234567*");
        driver.findElement(By.name("ime")).sendKeys("Nikola");
        driver.findElement(By.name("prezime")).sendKeys("Nikolic");
        driver.findElement(By.name("telefon")).sendKeys("066/889-213");
        driver.findElement(By.name("eposta")).sendKeys("nikola@gmail.com");
        driver.findElement(By.name("god_stud")).sendKeys("2");
        new Select(driver.findElement(By.name("diploma"))).selectByValue("0");
        driver.findElement(By.name("slika"))
                .sendKeys("C:\\Users\\Win 11\\Desktop\\DZ1\\slike\\slika4.jpg");

        driver.findElement(By.xpath("//input[@type='submit' and @value='Registruj se']")).click();

        try {

            String text = driver.switchTo().alert().getText();
            driver.switchTo().alert().accept();

            Assert.assertTrue(text.contains("Lozinka mora imati 8–12 karaktera"));
            System.out.println("TEST03 LOZINKA PREDUGA - PASS");

        } catch (Throwable e) {

            System.out.println("TEST03 LOZINKA PREDUGA - FAILED");
            Assert.fail("TEST03 LOZINKA PREDUGA - FAILED");
        }
    }

    @Test
    public void test04RegistracijaLozinkaBezVelikogSlova() {

        driver.findElement(By.linkText("Postani korisnik")).click();
        driver.findElement(By.cssSelector("input[type='radio'][value='student']")).click();
        driver.findElement(By.xpath("//input[@type='submit' and @value='Zapocni registraciju']")).click();

        driver.findElement(By.name("k_ime")).sendKeys("dimitrije03");
        driver.findElement(By.name("lozinka")).sendKeys("dimitrije1*");
        driver.findElement(By.name("lozinka_potvrdi")).sendKeys("dimitrije1*");
        driver.findElement(By.name("ime")).sendKeys("Dimitrije");
        driver.findElement(By.name("prezime")).sendKeys("Dimic");
        driver.findElement(By.name("telefon")).sendKeys("065/544-507");
        driver.findElement(By.name("eposta")).sendKeys("dimitrije@gmail.com");
        driver.findElement(By.name("god_stud")).sendKeys("3");
        new Select(driver.findElement(By.name("diploma"))).selectByValue("0");
        driver.findElement(By.name("slika"))
                .sendKeys("C:\\Users\\Win 11\\Desktop\\DZ1\\slike\\slika4.jpg");

        driver.findElement(By.xpath("//input[@type='submit' and @value='Registruj se']")).click();

        try {

            String text = driver.switchTo().alert().getText();
            driver.switchTo().alert().accept();

            Assert.assertTrue(text.contains("Lozinka nije u ispravnom formatu"));
            System.out.println("TEST04 LOZINKA BEZ VELIKOG SLOVA - PASS");

        } catch (Throwable e) {

            System.out.println("TEST04 LOZINKA BEZ VELIKOG SLOVA - FAILED");
            Assert.fail("TEST04 LOZINKA BEZ VELIKOG SLOVA - FAILED");
        }
    }

    @Test
    public void test05RegistracijaManjeOdTriMalaSlova() {

        driver.findElement(By.linkText("Postani korisnik")).click();
        driver.findElement(By.cssSelector("input[type='radio'][value='student']")).click();
        driver.findElement(By.xpath("//input[@type='submit' and @value='Zapocni registraciju']")).click();

        driver.findElement(By.name("k_ime")).sendKeys("nadjak01");
        driver.findElement(By.name("lozinka")).sendKeys("NaDJA1*");
        driver.findElement(By.name("lozinka_potvrdi")).sendKeys("NaDJA1*");
        driver.findElement(By.name("ime")).sendKeys("Nadja");
        driver.findElement(By.name("prezime")).sendKeys("Krunic");
        driver.findElement(By.name("telefon")).sendKeys("+387 64/777-888");
        driver.findElement(By.name("eposta")).sendKeys("nadja@gmail.com");
        driver.findElement(By.name("god_stud")).sendKeys("2");
        new Select(driver.findElement(By.name("diploma"))).selectByValue("0");
        driver.findElement(By.name("slika"))
                .sendKeys("C:\\Users\\Win 11\\Desktop\\DZ1\\slike\\slika4.jpg");

        driver.findElement(By.xpath("//input[@type='submit' and @value='Registruj se']")).click();

        try {

            String text = driver.switchTo().alert().getText();
            driver.switchTo().alert().accept();

            Assert.assertTrue(text.contains("Lozinka nije u ispravnom formatu"));
            System.out.println("TEST05 MANJE OD TRI MALA SLOVA - PASS");

        } catch (Throwable e) {
            System.out.println("TEST05 MANJE OD TRI MALA SLOVA - FAILED");
            Assert.fail("TEST05 MANJE OD TRI MALA SLOVA - FAILED");
        }
    }

    @Test
    public void test06RegistracijaBezCifre() {

        driver.findElement(By.linkText("Postani korisnik")).click();
        driver.findElement(By.cssSelector("input[type='radio'][value='student']")).click();
        driver.findElement(By.xpath("//input[@type='submit' and @value='Zapocni registraciju']")).click();

        driver.findElement(By.name("k_ime")).sendKeys("anja112");
        driver.findElement(By.name("lozinka")).sendKeys("Anjagxa*");
        driver.findElement(By.name("lozinka_potvrdi")).sendKeys("Anjagxa*");
        driver.findElement(By.name("ime")).sendKeys("Anja");
        driver.findElement(By.name("prezime")).sendKeys("Golubovic");
        driver.findElement(By.name("telefon")).sendKeys("065/333-444");
        driver.findElement(By.name("eposta")).sendKeys("anjag@gmail.com");
        driver.findElement(By.name("god_stud")).sendKeys("4");
        new Select(driver.findElement(By.name("diploma"))).selectByValue("0");
        driver.findElement(By.name("slika"))
                .sendKeys("C:\\Users\\Win 11\\Desktop\\DZ1\\slike\\slika4.jpg");

        driver.findElement(By.xpath("//input[@type='submit' and @value='Registruj se']")).click();

        try {

            String text = driver.switchTo().alert().getText();
            driver.switchTo().alert().accept();

            Assert.assertTrue(text.contains("Lozinka nije u ispravnom formatu"));
            System.out.println("TEST06 BEZ CIFRE U LOZINCI - PASS");

        } catch (Throwable e) {

            System.out.println("TEST06 BEZ CIFRE U LOZINCI - FAILED");
            Assert.fail("TEST06 BEZ CIFRE U LOZINCI - FAILED");
        }
    }

    @Test
    public void test07RegistracijaBezSpecijalnogZnaka() {

        driver.findElement(By.linkText("Postani korisnik")).click();
        driver.findElement(By.cssSelector("input[type='radio'][value='student']")).click();
        driver.findElement(By.xpath("//input[@type='submit' and @value='Zapocni registraciju']")).click();

        driver.findElement(By.name("k_ime")).sendKeys("maja1234");
        driver.findElement(By.name("lozinka")).sendKeys("MajaP123");
        driver.findElement(By.name("lozinka_potvrdi")).sendKeys("MajaP123");
        driver.findElement(By.name("ime")).sendKeys("Maja");
        driver.findElement(By.name("prezime")).sendKeys("Perović");
        driver.findElement(By.name("telefon")).sendKeys("+381 64 5694-000");
        driver.findElement(By.name("eposta")).sendKeys("majaperovic@gmail.com");
        driver.findElement(By.name("god_stud")).sendKeys("3");
        new Select(driver.findElement(By.name("diploma"))).selectByValue("0");

        driver.findElement(By.name("slika"))
                .sendKeys("C:\\Users\\Win 11\\Desktop\\DZ1\\slike\\slika4.jpg");

        driver.findElement(By.xpath("//input[@type='submit' and @value='Registruj se']")).click();

        try {

            String text = driver.switchTo().alert().getText();
            driver.switchTo().alert().accept();

            Assert.assertTrue(text.contains("Lozinka nije u ispravnom formatu"));
            System.out.println("TEST07 BEZ SPECIJALNOG ZNAKA - PASS");

        } catch (Throwable e) {

            System.out.println("TEST07 BEZ SPECIJALNOG ZNAKA - FAILED");
            Assert.fail("TEST07 BEZ SPECIJALNOG ZNAKA - FAILED");
        }
    }

    @Test
    public void test08RegistracijaLozinkaPocinjeCifrom() {

        driver.findElement(By.linkText("Postani korisnik")).click();
        driver.findElement(By.cssSelector("input[type='radio'][value='student']")).click();
        driver.findElement(By.xpath("//input[@type='submit' and @value='Zapocni registraciju']")).click();

        driver.findElement(By.name("k_ime")).sendKeys("luka123");
        driver.findElement(By.name("lozinka")).sendKeys("1Luka123*");
        driver.findElement(By.name("lozinka_potvrdi")).sendKeys("1Luka123*");
        driver.findElement(By.name("ime")).sendKeys("Luka");
        driver.findElement(By.name("prezime")).sendKeys("Lukic");
        driver.findElement(By.name("telefon")).sendKeys("+38165111999");
        driver.findElement(By.name("eposta")).sendKeys("lukalukic@gmail.com");
        driver.findElement(By.name("god_stud")).sendKeys("3");

        new Select(driver.findElement(By.name("diploma"))).selectByValue("0");
        driver.findElement(By.name("slika"))
                .sendKeys("C:\\Users\\Win 11\\Desktop\\DZ1\\slike\\slika4.jpg");

        driver.findElement(By.xpath("//input[@type='submit' and @value='Registruj se']")).click();

        try {

            String text = driver.switchTo().alert().getText();
            driver.switchTo().alert().accept();

            Assert.assertTrue(text.contains("Lozinka nije u ispravnom formatu"));
            System.out.println("TEST08 LOZINKA POCINJE CIFROM - PASS");

        } catch (Throwable e) {

            System.out.println("TEST08 LOZINKA POCINJE CIFROM - FAILED");
            Assert.assertTrue(false);
        }
    }

    @Test
    public void test09RegistracijaLozinkaPocinjeSpecijalnimZnakom() {

        driver.findElement(By.linkText("Postani korisnik")).click();
        driver.findElement(By.cssSelector("input[type='radio'][value='student']")).click();
        driver.findElement(By.xpath("//input[@type='submit' and @value='Zapocni registraciju']")).click();

        driver.findElement(By.name("k_ime")).sendKeys("jovana123");
        driver.findElement(By.name("lozinka")).sendKeys("*Jovana123*");
        driver.findElement(By.name("lozinka_potvrdi")).sendKeys("*Jovana123*");
        driver.findElement(By.name("ime")).sendKeys("Jovana");
        driver.findElement(By.name("prezime")).sendKeys("Ceganjac");
        driver.findElement(By.name("telefon")).sendKeys("+38166123456");
        driver.findElement(By.name("eposta")).sendKeys("jovanaceganjac@gmail.com");
        driver.findElement(By.name("god_stud")).sendKeys("3");

        new Select(driver.findElement(By.name("diploma"))).selectByValue("0");
        driver.findElement(By.name("slika"))
                .sendKeys("C:\\Users\\Win 11\\Desktop\\DZ1\\slike\\slika4.jpg");

        driver.findElement(By.xpath("//input[@type='submit' and @value='Registruj se']")).click();

        try {

            String text = driver.switchTo().alert().getText();
            driver.switchTo().alert().accept();

            Assert.assertTrue(text.contains("Lozinka nije u ispravnom formatu"));
            System.out.println("TEST09 LOZINKA POCINJE SPECIJALNIM ZNAKOM - PASS");

        } catch (Throwable e) {
            System.out.println("TEST09 LOZINKA POCINJE SPECIJALNIM ZNAKOM - FAILED");
            Assert.assertTrue(false);
        }
    }

    @Test
    public void test10RegistracijaLozinkaDvaUzastopnaIstaZnaka() {

        driver.findElement(By.linkText("Postani korisnik")).click();
        driver.findElement(By.cssSelector("input[type='radio'][value='student']")).click();
        driver.findElement(By.xpath("//input[@type='submit' and @value='Zapocni registraciju']")).click();

        driver.findElement(By.name("k_ime")).sendKeys("milica123");
        driver.findElement(By.name("lozinka")).sendKeys("mIlicaa123*");
        driver.findElement(By.name("lozinka_potvrdi")).sendKeys("mIlicaa123*");
        driver.findElement(By.name("ime")).sendKeys("Milica");
        driver.findElement(By.name("prezime")).sendKeys("Stojanovic");
        driver.findElement(By.name("telefon")).sendKeys("+38165321654");
        driver.findElement(By.name("eposta")).sendKeys("milicastojanovic@gmail.com");
        driver.findElement(By.name("god_stud")).sendKeys("3");

        new Select(driver.findElement(By.name("diploma"))).selectByValue("0");
        driver.findElement(By.name("slika"))
                .sendKeys("C:\\Users\\Win 11\\Desktop\\DZ1\\slike\\slika4.jpg");

        driver.findElement(By.xpath("//input[@type='submit' and @value='Registruj se']")).click();

        try {
            String text = driver.switchTo().alert().getText();
            driver.switchTo().alert().accept();

            Assert.assertTrue(text.contains("Lozinka nije u ispravnom formatu"));
            System.out.println("TEST10 DVA ISTA UZASTOPNA ZNAKA - PASS");

        } catch (Throwable e) {
            System.out.println("TEST10 DVA ISTA UZASTOPNA ZNAKA - FAILED");
            Assert.assertTrue(false);
        }
    }

    @Test
    public void test11RegistracijaKorisnickoImeVecPostoji() {

        driver.findElement(By.linkText("Postani korisnik")).click();
        driver.findElement(By.cssSelector("input[type='radio'][value='student']")).click();
        driver.findElement(By.xpath("//input[@type='submit' and @value='Zapocni registraciju']")).click();

        driver.findElement(By.name("k_ime")).sendKeys("maja");
        driver.findElement(By.name("lozinka")).sendKeys("Maja1234*");
        driver.findElement(By.name("lozinka_potvrdi")).sendKeys("Maja1234*");
        driver.findElement(By.name("ime")).sendKeys("Maja");
        driver.findElement(By.name("prezime")).sendKeys("Janjic");
        driver.findElement(By.name("telefon")).sendKeys("+382655694076");
        driver.findElement(By.name("eposta")).sendKeys("majajanjic@gmail.com");
        driver.findElement(By.name("god_stud")).sendKeys("1");

        new Select(driver.findElement(By.name("diploma"))).selectByValue("0");
        driver.findElement(By.name("slika"))
                .sendKeys("C:\\Users\\Win 11\\Desktop\\DZ1\\slike\\slika4.jpg");

        driver.findElement(By.xpath("//input[@type='submit' and @value='Registruj se']")).click();

        try {

            WebElement poruka = driver.findElement(By.xpath("//body[contains(text(),'Korisnicko ime vec postoji u sistemu')]"));
            String tekst = poruka.getText().trim();

            Assert.assertTrue(tekst.contains("Korisnicko ime vec postoji u sistemu! Unesite drugo korisnicko ime."));
            System.out.println("TEST11 KORISNICKO IME VEC POSTOJI - PASS");

        } catch (Throwable e) {
            System.out.println("TEST11 KORISNICKO IME VEC POSTOJI - FAILED");
            Assert.fail("TEST11 KORISNICKO IME VEC POSTOJI - FAILED");
        }
    }

    @Test
    public void test15RegistracijaEmailBezTacke() {

        driver.findElement(By.linkText("Postani korisnik")).click();
        driver.findElement(By.cssSelector("input[type='radio'][value='student']")).click();
        driver.findElement(By.xpath("//input[@type='submit' and @value='Zapocni registraciju']")).click();

        driver.findElement(By.name("k_ime")).sendKeys("ljubisa123");
        driver.findElement(By.name("lozinka")).sendKeys("Ljubisa123*");
        driver.findElement(By.name("lozinka_potvrdi")).sendKeys("Ljubisa123*");
        driver.findElement(By.name("ime")).sendKeys("Ljubisa");
        driver.findElement(By.name("prezime")).sendKeys("Špirto");
        driver.findElement(By.name("telefon")).sendKeys("+38164000123");
        driver.findElement(By.name("eposta")).sendKeys("ljubisaspirto@gmailcom");
        driver.findElement(By.name("god_stud")).sendKeys("2");

        new Select(driver.findElement(By.name("diploma"))).selectByValue("0");
        driver.findElement(By.name("slika"))
                .sendKeys("C:\\Users\\Win 11\\Desktop\\DZ1\\slike\\slika4.jpg");

        driver.findElement(By.xpath("//input[@type='submit' and @value='Registruj se']")).click();

        try {
            String text = driver.switchTo().alert().getText();
            driver.switchTo().alert().accept();

            Assert.assertTrue(text.contains("Email nije u dobrom formatu"));
            System.out.println("TEST15 EMAIL BEZ TACKe - PASS");

        } catch (Throwable e) {
            System.out.println("TEST15 EMAIL BEZ TACKE - FAILED");
            Assert.fail("TEST15 EMAIL BEZ TACKE - FAILED");
        }
    }

    @Test
    public void test21RegistracijaGodinaStudijaManjaOd1() {

        driver.findElement(By.linkText("Postani korisnik")).click();
        driver.findElement(By.cssSelector("input[type='radio'][value='student']")).click();
        driver.findElement(By.xpath("//input[@type='submit' and @value='Zapocni registraciju']")).click();

        driver.findElement(By.name("k_ime")).sendKeys("marija123");
        driver.findElement(By.name("lozinka")).sendKeys("Marija123*");
        driver.findElement(By.name("lozinka_potvrdi")).sendKeys("Marija123*");
        driver.findElement(By.name("ime")).sendKeys("Marija");
        driver.findElement(By.name("prezime")).sendKeys("Marijanovic");
        driver.findElement(By.name("telefon")).sendKeys("+381645694444");
        driver.findElement(By.name("eposta")).sendKeys("marija@gmail.com");
        driver.findElement(By.name("god_stud")).sendKeys("-1");

        new Select(driver.findElement(By.name("diploma"))).selectByValue("0");
        driver.findElement(By.name("slika"))
                .sendKeys("C:\\Users\\Win 11\\Desktop\\DZ1\\slike\\slika4.jpg");

        driver.findElement(By.xpath("//input[@type='submit' and @value='Registruj se']")).click();

        try {
            String text = driver.switchTo().alert().getText();
            driver.switchTo().alert().accept();

            Assert.assertTrue(text.contains("Godina studija nije u dobrom opsegu"));
            System.out.println("TEST21 GODINA STUDIJA < 1 - PASS");

        } catch (Throwable e) {
            System.out.println("TEST21 GODINA STUDIJA < 1 - FAILED");
            Assert.fail("TEST21 GODINA STUDIJA < 1 - FAILED");
        }
    }

    @Test
    public void test22RegistracijaGodinaStudijaVecaOd4() {

        driver.findElement(By.linkText("Postani korisnik")).click();
        driver.findElement(By.cssSelector("input[type='radio'][value='student']")).click();
        driver.findElement(By.xpath("//input[@type='submit' and @value='Zapocni registraciju']")).click();

        driver.findElement(By.name("k_ime")).sendKeys("marko123");
        driver.findElement(By.name("lozinka")).sendKeys("Marko123*");
        driver.findElement(By.name("lozinka_potvrdi")).sendKeys("Marko123*");
        driver.findElement(By.name("ime")).sendKeys("Marko");
        driver.findElement(By.name("prezime")).sendKeys("Markovic");
        driver.findElement(By.name("telefon")).sendKeys("+381640000076");
        driver.findElement(By.name("eposta")).sendKeys("marko@gmail.com");
        driver.findElement(By.name("god_stud")).sendKeys("8"); 

        new Select(driver.findElement(By.name("diploma"))).selectByValue("0");
        driver.findElement(By.name("slika"))
                .sendKeys("C:\\Users\\Win 11\\Desktop\\DZ1\\slike\\slika4.jpg");

        driver.findElement(By.xpath("//input[@type='submit' and @value='Registruj se']")).click();

        try {
            String text = driver.switchTo().alert().getText();
            driver.switchTo().alert().accept();

            Assert.assertTrue(text.contains("Godina studija nije u dobrom opsegu"));
            System.out.println("TEST22 GODINA STUDIJA > 4 - PASS");

        } catch (Throwable e) {
            System.out.println("TEST22 GODINA STUDIJA > 4 - FAILED");
            Assert.fail("TEST22 GODINA STUDIJA > 4 - FAILED");
        }
    }

    @Test
    public void test24RegistracijaSlikaPrevelika() {

        driver.findElement(By.linkText("Postani korisnik")).click();
        driver.findElement(By.cssSelector("input[type='radio'][value='student']")).click();
        driver.findElement(By.xpath("//input[@type='submit' and @value='Zapocni registraciju']")).click();

        driver.findElement(By.name("k_ime")).sendKeys("aleksa123");
        driver.findElement(By.name("lozinka")).sendKeys("Aleksa123*");
        driver.findElement(By.name("lozinka_potvrdi")).sendKeys("Aleksa123*");
        driver.findElement(By.name("ime")).sendKeys("Aleksa");
        driver.findElement(By.name("prezime")).sendKeys("Aleksic");
        driver.findElement(By.name("telefon")).sendKeys("+381645694765");
        driver.findElement(By.name("eposta")).sendKeys("aleksa@gmail.com");
        driver.findElement(By.name("god_stud")).sendKeys("3");

        new Select(driver.findElement(By.name("diploma"))).selectByValue("0");

        driver.findElement(By.name("slika"))
                .sendKeys("C:\\Users\\Win 11\\Desktop\\DZ1\\slike\\slika400x400.png");

        driver.findElement(By.xpath("//input[@type='submit' and @value='Registruj se']")).click();

        try {
            String text = driver.switchTo().alert().getText();
            driver.switchTo().alert().accept();

            Assert.assertTrue(text.contains("Velicina slike nije ispravna"));
            System.out.println("TEST24 SLIKA PREVELIKA - PASS");

        } catch (Throwable e) {
            System.out.println("TEST24 SLIKA PREVELIKA - FAILED");
            Assert.fail("TEST24 SLIKA PREVELIKA - FAILED");
        }
    }

    @Test
    public void test25RegistracijaSlikaPremala() {

        driver.findElement(By.linkText("Postani korisnik")).click();
        driver.findElement(By.cssSelector("input[type='radio'][value='student']")).click();
        driver.findElement(By.xpath("//input[@type='submit' and @value='Zapocni registraciju']")).click();

        driver.findElement(By.name("k_ime")).sendKeys("andrija123");
        driver.findElement(By.name("lozinka")).sendKeys("Andrija123*");
        driver.findElement(By.name("lozinka_potvrdi")).sendKeys("Andrija123*");
        driver.findElement(By.name("ime")).sendKeys("Andrija");
        driver.findElement(By.name("prezime")).sendKeys("Andric");
        driver.findElement(By.name("telefon")).sendKeys("+381648989076");
        driver.findElement(By.name("eposta")).sendKeys("andrija@gmail.com");
        driver.findElement(By.name("god_stud")).sendKeys("3");

        new Select(driver.findElement(By.name("diploma"))).selectByValue("0");

        driver.findElement(By.name("slika"))
                .sendKeys("C:\\Users\\Win 11\\Desktop\\DZ1\\slike\\slika50x50.png");

        driver.findElement(By.xpath("//input[@type='submit' and @value='Registruj se']")).click();

        try {
            String text = driver.switchTo().alert().getText();
            driver.switchTo().alert().accept();

            Assert.assertTrue(text.contains("Velicina slike nije ispravna"));
            System.out.println("TEST25 SLIKA PREMALA - PASS");

        } catch (Throwable e) {
            System.out.println("TEST25 SLIKA PREMALA - FAILED");
            Assert.fail("TEST25 SLIKA PREMALA - FAILED");
        }
    }

    @Test
    public void test26RegistracijaTelefonNedozvoljenZnak() {

        driver.findElement(By.linkText("Postani korisnik")).click();
        driver.findElement(By.cssSelector("input[type='radio'][value='student']")).click();
        driver.findElement(By.xpath("//input[@type='submit' and @value='Zapocni registraciju']")).click();

        driver.findElement(By.name("k_ime")).sendKeys("tina123");
        driver.findElement(By.name("lozinka")).sendKeys("Tina123*");
        driver.findElement(By.name("lozinka_potvrdi")).sendKeys("Tina123*");
        driver.findElement(By.name("ime")).sendKeys("Tina");
        driver.findElement(By.name("prezime")).sendKeys("Špirto");
        driver.findElement(By.name("telefon")).sendKeys("+387&650-123");
        driver.findElement(By.name("eposta")).sendKeys("tinaspirto@gmail.com");
        driver.findElement(By.name("god_stud")).sendKeys("3");
        new Select(driver.findElement(By.name("diploma"))).selectByValue("0");

        driver.findElement(By.name("slika"))
                .sendKeys("C:\\Users\\Win 11\\Desktop\\DZ1\\slike\\slika4.jpg");

        driver.findElement(By.xpath("//input[@type='submit' and @value='Registruj se']")).click();

        try {

            String text = driver.switchTo().alert().getText();
            driver.switchTo().alert().accept();

            Assert.assertTrue(text.contains("Telefon nije u dobrom formatu"));
            System.out.println("TEST26 TELEFON NEDOZVOLJEN ZNAK - PASS");

        } catch (Throwable e) {

            System.out.println("TEST26 TELEFON NEDOZVOLJEN ZNAK - FAILED");
            Assert.fail("TEST26 TELEFON NEDOZVOLJEN ZNAK - FAILED");
        }
    }

    @Test
    public void test27RegistracijaTelefonSlova() {

        driver.findElement(By.linkText("Postani korisnik")).click();
        driver.findElement(By.cssSelector("input[type='radio'][value='student']")).click();
        driver.findElement(By.xpath("//input[@type='submit' and @value='Zapocni registraciju']")).click();

        driver.findElement(By.name("k_ime")).sendKeys("ivana123");
        driver.findElement(By.name("lozinka")).sendKeys("Ivana123*");
        driver.findElement(By.name("lozinka_potvrdi")).sendKeys("Ivana123*");
        driver.findElement(By.name("ime")).sendKeys("Ivana");
        driver.findElement(By.name("prezime")).sendKeys("Ivanovic");
        driver.findElement(By.name("telefon")).sendKeys("+387/abc-123");
        driver.findElement(By.name("eposta")).sendKeys("ivana@gmail.com");
        driver.findElement(By.name("god_stud")).sendKeys("3");
        new Select(driver.findElement(By.name("diploma"))).selectByValue("0");

        driver.findElement(By.name("slika"))
                .sendKeys("C:\\Users\\Win 11\\Desktop\\DZ1\\slike\\slika4.jpg");

        driver.findElement(By.xpath("//input[@type='submit' and @value='Registruj se']")).click();

        try {

            String text = driver.switchTo().alert().getText();
            driver.switchTo().alert().accept();

            Assert.assertTrue(text.contains("Telefon nije u dobrom formatu"));
            System.out.println("TEST27 TELEFON SLOVA - PASS");

        } catch (Throwable e) {

            System.out.println("TEST27 TELEFON SLOVA - FAILED");
            Assert.fail("TEST27 TELEFON SLOVA - FAILED");
        }
    }

    @Test
    public void test32RegistracijaIspravnaLozinkaPrvoMaloSlovo() {
        driver.findElement(By.linkText("Postani korisnik")).click();
        Assert.assertTrue(driver.getCurrentUrl().contains("registracija.php"));

        driver.findElement(By.cssSelector("input[type='radio'][value='admin']")).click();
        driver.findElement(By.xpath("//input[@type='submit' and @value='Zapocni registraciju']")).click();
        Assert.assertTrue(driver.getCurrentUrl().contains("reg_student.php"));

        driver.findElement(By.name("k_ime")).sendKeys("drazen");
        driver.findElement(By.name("lozinka")).sendKeys("dRazen123*");
        driver.findElement(By.name("lozinka_potvrdi")).sendKeys("dRazen123*");
        driver.findElement(By.name("ime")).sendKeys("Drazen");
        driver.findElement(By.name("prezime")).sendKeys("Draskovic");
        driver.findElement(By.name("telefon")).sendKeys("+381645665056");
        driver.findElement(By.name("eposta")).sendKeys("drazen@gmail.com");
       

        WebElement fileInput = driver.findElement(By.name("slika"));
        fileInput.sendKeys("C:\\Users\\Win 11\\Desktop\\DZ1\\slike\\slika4.jpg");

        driver.findElement(By.xpath("//input[@type='submit' and @value='Registruj se']")).click();

        try {
            WebElement poruka = driver.findElement(By.xpath("//center"));
            String tekst = poruka.getText().trim();

            Assert.assertTrue(tekst.contains("Uspesno ste se registrovali!"));

            WebElement link = driver.findElement(By.xpath("//center/a"));
            Assert.assertEquals(link.getText().trim(), "Nazad na prijavu.");

            System.out.println("TEST32 ISPRAVNA LOZINKA PRVO MALO SLOVO - PASS");
        } catch (Throwable e) {
            System.out.println("TEST32 ISPRAVNA LOZINKA PRVO MALO SLOVO - FAILED: ");
        }
    }

    @Test
    public void test38RegistracijaKompanijeUspesna() {

        driver.findElement(By.linkText("Postani korisnik")).click();
        Assert.assertTrue(driver.getCurrentUrl().contains("registracija.php"));

        driver.findElement(By.cssSelector("input[type='radio'][value='kompanija']")).click();
        driver.findElement(By.xpath("//input[@type='submit' and @value='Zapocni registraciju']")).click();
        Assert.assertTrue(driver.getCurrentUrl().contains("reg_komp.php"));

        driver.findElement(By.name("k_ime")).sendKeys("mojaFirma");
        driver.findElement(By.name("lozinka")).sendKeys("Firma123*");
        driver.findElement(By.name("lozinka_potvrdi")).sendKeys("Firma123*");
        driver.findElement(By.name("naziv")).sendKeys("Kompanija");
        driver.findElement(By.name("grad")).sendKeys("Beograd");
        driver.findElement(By.name("adresa")).sendKeys("Bulevar Mihajla Pupina, 107");
        driver.findElement(By.name("direktor")).sendKeys("Maja Špirto");
        driver.findElement(By.name("pib")).sendKeys("100");
        driver.findElement(By.name("brzaposlenih")).sendKeys("5");
        driver.findElement(By.name("eposta")).sendKeys("maja@gmail.com");
        driver.findElement(By.name("vebsajt")).sendKeys("www.kompanija.com");

        new Select(driver.findElement(By.name("delatnost"))).selectByVisibleText("IT");
        driver.findElement(By.name("specijalnost")).sendKeys("Testiranje softvera");

        WebElement fileInput = driver.findElement(By.name("logo"));
        fileInput.sendKeys("C:\\Users\\Win 11\\Desktop\\DZ1\\logoi\\logo5.png");

        driver.findElement(By.xpath("//input[@type='submit' and @value='Registruj se']")).click();

        try {
            String poruka = driver.findElement(By.xpath("//body[contains(text(),'Uspesna registracija')]")).getText().trim();
            Assert.assertTrue(poruka.contains("Uspesna registracija"));

            WebElement link = driver.findElement(By.xpath("//a[normalize-space(text())='Prijavi se.']"));
            String linkText = link.getText().trim();
            Assert.assertEquals("Prijavi se.", linkText);

            System.out.println("TEST38 REGISTRACIJA KOMPANIJE USPESNA - PASS");

        } catch (Throwable e) {
            System.out.println("TEST38 REGISTRACIJA KOMPANIJE USPESNA - FAILED: " + e.getMessage());
            throw e;
        }

    }

    @Test
    public void test50RegistracijaKompanijeNeuspesnaPIBSlovo() {

        driver.findElement(By.linkText("Postani korisnik")).click();
        Assert.assertTrue(driver.getCurrentUrl().contains("registracija.php"));

        driver.findElement(By.cssSelector("input[type='radio'][value='kompanija']")).click();
        driver.findElement(By.xpath("//input[@type='submit' and @value='Zapocni registraciju']")).click();
        Assert.assertTrue(driver.getCurrentUrl().contains("reg_komp.php"));

        driver.findElement(By.name("k_ime")).sendKeys("kompanijaPIB");
        driver.findElement(By.name("lozinka")).sendKeys("Komp1234#");
        driver.findElement(By.name("lozinka_potvrdi")).sendKeys("Komp1234#");
        driver.findElement(By.name("naziv")).sendKeys("Kompanija");
        driver.findElement(By.name("grad")).sendKeys("Beograd");
        driver.findElement(By.name("adresa")).sendKeys("Bulevar Mihajla Pupina, 107");
        driver.findElement(By.name("direktor")).sendKeys("Maja Špirto");
        driver.findElement(By.name("pib")).sendKeys("123a");
        driver.findElement(By.name("brzaposlenih")).sendKeys("5");
        driver.findElement(By.name("eposta")).sendKeys("majaspirto@gmail.com");
        driver.findElement(By.name("vebsajt")).sendKeys("www.kompanija.com");

        new Select(driver.findElement(By.name("delatnost"))).selectByVisibleText("IT");
        driver.findElement(By.name("specijalnost")).sendKeys("Testiranje softvera");

        WebElement fileInput = driver.findElement(By.name("logo"));
        fileInput.sendKeys("C:\\Users\\Win 11\\Desktop\\DZ1\\logoi\\logo5.png");

        driver.findElement(By.xpath("//input[@type='submit' and @value='Registruj se']")).click();

        try {

            String text = driver.switchTo().alert().getText();
            driver.switchTo().alert().accept();

            Assert.assertTrue(text.contains("PIB nije u dobrom formatu"));
            System.out.println("TEST50 PIB SLOVO - PASS");

        } catch (Throwable e) {
            System.out.println("TEST50 PIB SLOVO - FAILED");
            Assert.fail("TEST50 PIB SLOVO - FAILED");
        }
    }

    @Test
    public void test51RegistracijaKompanijeNeuspesnaPIBSpecijalniKarakter() {

        driver.findElement(By.linkText("Postani korisnik")).click();
        Assert.assertTrue(driver.getCurrentUrl().contains("registracija.php"));

        driver.findElement(By.cssSelector("input[type='radio'][value='kompanija']")).click();
        driver.findElement(By.xpath("//input[@type='submit' and @value='Zapocni registraciju']")).click();
        Assert.assertTrue(driver.getCurrentUrl().contains("reg_komp.php"));

        driver.findElement(By.name("k_ime")).sendKeys("kompanijaPIBSpec");
        driver.findElement(By.name("lozinka")).sendKeys("Komp1234#");
        driver.findElement(By.name("lozinka_potvrdi")).sendKeys("Komp1234#");
        driver.findElement(By.name("naziv")).sendKeys("Kompanija");
        driver.findElement(By.name("grad")).sendKeys("Beograd");
        driver.findElement(By.name("adresa")).sendKeys("Bulevar Mihajla Pupina, 107");
        driver.findElement(By.name("direktor")).sendKeys("Maja Špirto");
        driver.findElement(By.name("pib")).sendKeys("123*");
        driver.findElement(By.name("brzaposlenih")).sendKeys("5");
        driver.findElement(By.name("eposta")).sendKeys("majaspirto@gmail.com");
        driver.findElement(By.name("vebsajt")).sendKeys("www.kompanija.com");

        new Select(driver.findElement(By.name("delatnost"))).selectByVisibleText("IT");
        driver.findElement(By.name("specijalnost")).sendKeys("Testiranje softvera");

        WebElement fileInput = driver.findElement(By.name("logo"));
        fileInput.sendKeys("C:\\Users\\Win 11\\Desktop\\DZ1\\logoi\\logo5.png");

        driver.findElement(By.xpath("//input[@type='submit' and @value='Registruj se']")).click();

        try {

            String text = driver.switchTo().alert().getText();
            driver.switchTo().alert().accept();

            Assert.assertTrue(text.contains("PIB nije u dobrom formatu"));
            System.out.println("TEST51 PIB SPECIJALNI KARAKTER - PASS");

        } catch (Throwable e) {
            System.out.println("TEST51 PIB SPECIJALNI KARAKTER - FAILED");
            Assert.fail("TEST51 PIB SPECIJALNI KARAKTER - FAILED");
        }
    }

    @Test
    public void test54RegistracijaKompanijeNeuspesnaBrojZaposlenihManjiOd1() {

        driver.findElement(By.linkText("Postani korisnik")).click();
        Assert.assertTrue(driver.getCurrentUrl().contains("registracija.php"));

        driver.findElement(By.cssSelector("input[type='radio'][value='kompanija']")).click();
        driver.findElement(By.xpath("//input[@type='submit' and @value='Zapocni registraciju']")).click();
        Assert.assertTrue(driver.getCurrentUrl().contains("reg_komp.php"));

        driver.findElement(By.name("k_ime")).sendKeys("kompanijaBrZapNeg");
        driver.findElement(By.name("lozinka")).sendKeys("Komp1234#");
        driver.findElement(By.name("lozinka_potvrdi")).sendKeys("Komp1234#");
        driver.findElement(By.name("naziv")).sendKeys("Kompanija");
        driver.findElement(By.name("grad")).sendKeys("Beograd");
        driver.findElement(By.name("adresa")).sendKeys("Bulevar Mihajla Pupina, 107");
        driver.findElement(By.name("direktor")).sendKeys("Maja Špirto");
        driver.findElement(By.name("pib")).sendKeys("1234");
        driver.findElement(By.name("brzaposlenih")).sendKeys("-5");
        driver.findElement(By.name("eposta")).sendKeys("majaspirto@gmail.com");
        driver.findElement(By.name("vebsajt")).sendKeys("www.kompanija.com");

        new Select(driver.findElement(By.name("delatnost"))).selectByVisibleText("IT");
        driver.findElement(By.name("specijalnost")).sendKeys("Testiranje softvera");

        WebElement fileInput = driver.findElement(By.name("logo"));
        fileInput.sendKeys("C:\\Users\\Win 11\\Desktop\\DZ1\\logoi\\logo5.png");

        driver.findElement(By.xpath("//input[@type='submit' and @value='Registruj se']")).click();

        try {

            String text = driver.switchTo().alert().getText();
            driver.switchTo().alert().accept();

            Assert.assertTrue(text.contains("Broj zaposlenih nije u dobrom formatu"));
            System.out.println("TEST54 BROJ ZAPOSLENIH - PASS");

        } catch (Throwable e) {
            System.out.println("TEST54 BROJ ZAPOSLENIH - FAILED");
            Assert.fail("TEST54 BROJ ZAPOSLENIH - FAILED");
        }
    }

    @Test
    public void test55RegistracijaKompanijeNeuspesnaVebSajtSpecijalniKarakter() {

        driver.findElement(By.linkText("Postani korisnik")).click();
        Assert.assertTrue(driver.getCurrentUrl().contains("registracija.php"));

        driver.findElement(By.cssSelector("input[type='radio'][value='kompanija']")).click();
        driver.findElement(By.xpath("//input[@type='submit' and @value='Zapocni registraciju']")).click();
        Assert.assertTrue(driver.getCurrentUrl().contains("reg_komp.php"));

        driver.findElement(By.name("k_ime")).sendKeys("kompanijaWeb");
        driver.findElement(By.name("lozinka")).sendKeys("Komp1234#");
        driver.findElement(By.name("lozinka_potvrdi")).sendKeys("Komp1234#");
        driver.findElement(By.name("naziv")).sendKeys("Kompanija");
        driver.findElement(By.name("grad")).sendKeys("Beograd");
        driver.findElement(By.name("adresa")).sendKeys("Bulevar Mihajla Pupina, 107");
        driver.findElement(By.name("direktor")).sendKeys("Maja Špirto");
        driver.findElement(By.name("pib")).sendKeys("1234");
        driver.findElement(By.name("brzaposlenih")).sendKeys("5");
        driver.findElement(By.name("eposta")).sendKeys("majaspirto@gmail.com");
        driver.findElement(By.name("vebsajt")).sendKeys("ww@w.kompanija.com");

        new Select(driver.findElement(By.name("delatnost"))).selectByVisibleText("IT");
        driver.findElement(By.name("specijalnost")).sendKeys("Testiranje softvera");

        WebElement fileInput = driver.findElement(By.name("logo"));
        fileInput.sendKeys("C:\\Users\\Win 11\\Desktop\\DZ1\\logoi\\logo5.png");

        driver.findElement(By.xpath("//input[@type='submit' and @value='Registruj se']")).click();

        try {

            String text = driver.switchTo().alert().getText();
            driver.switchTo().alert().accept();

            Assert.assertTrue(text.contains("Veb sajt nije u dobrom formatu"));
            System.out.println("TEST55 VEB SAJT SPECIJALNI KARAKTER - PASS");

        } catch (Throwable e) {
            System.out.println("TEST55 VEB SAJT SPECIJALNI KARAKTER - FAILED");
            Assert.fail("TEST55 VEB SAJT SPECIJALNI KARAKTER - FAILED");
        }
    }

    @Test
    public void test56RegistracijaKompanijeNeuspesnaSpecijalnostSpecijalniKarakteri() {

        driver.findElement(By.linkText("Postani korisnik")).click();
        Assert.assertTrue(driver.getCurrentUrl().contains("registracija.php"));

        driver.findElement(By.cssSelector("input[type='radio'][value='kompanija']")).click();
        driver.findElement(By.xpath("//input[@type='submit' and @value='Zapocni registraciju']")).click();
        Assert.assertTrue(driver.getCurrentUrl().contains("reg_komp.php"));

        driver.findElement(By.name("k_ime")).sendKeys("kompanijaSpec");
        driver.findElement(By.name("lozinka")).sendKeys("Komp1234#");
        driver.findElement(By.name("lozinka_potvrdi")).sendKeys("Komp1234#");
        driver.findElement(By.name("naziv")).sendKeys("Kompanija");
        driver.findElement(By.name("grad")).sendKeys("Beograd");
        driver.findElement(By.name("adresa")).sendKeys("Bulevar Mihajla Pupina, 107");
        driver.findElement(By.name("direktor")).sendKeys("Maja Špirto");
        driver.findElement(By.name("pib")).sendKeys("1234");
        driver.findElement(By.name("brzaposlenih")).sendKeys("5");
        driver.findElement(By.name("eposta")).sendKeys("majaspirto@gmail.com");
        driver.findElement(By.name("vebsajt")).sendKeys("www.kompanija.com");

        new Select(driver.findElement(By.name("delatnost"))).selectByVisibleText("IT");

        driver.findElement(By.name("specijalnost")).sendKeys("Testiranje +*softvera");

        WebElement fileInput = driver.findElement(By.name("logo"));
        fileInput.sendKeys("C:\\Users\\Win 11\\Desktop\\DZ1\\logoi\\logo5.png");

        driver.findElement(By.xpath("//input[@type='submit' and @value='Registruj se']")).click();

        try {

            String text = driver.switchTo().alert().getText();
            driver.switchTo().alert().accept();

            Assert.assertTrue(text.contains("Uza specijalnost nije u dobrom formatu"));
            System.out.println("TEST56 UZA SPECIJALNOST SPECIJALNI KARAKTERI - PASS");

        } catch (Throwable e) {
            System.out.println("TEST56 UZA SPECIJALNOST SPECIJALNI KARAKTERI - FAILED");
            Assert.fail("TEST56 UZA SPECIJALNOST SPECIJALNI KARAKTERI - FAILED");
        }
    }

    @Test
    public void test85RegistracijaNeuspesnaLozinkeNisuIste() {

        driver.findElement(By.linkText("Postani korisnik")).click();
        Assert.assertTrue(driver.getCurrentUrl().contains("registracija.php"));

        driver.findElement(By.cssSelector("input[type='radio'][value='student']")).click();
        driver.findElement(By.xpath("//input[@type='submit' and @value='Zapocni registraciju']")).click();
        Assert.assertTrue(driver.getCurrentUrl().contains("reg_student.php"));

        driver.findElement(By.name("k_ime")).sendKeys("Sandra123");
        driver.findElement(By.name("lozinka")).sendKeys("Sandra123*");
        driver.findElement(By.name("lozinka_potvrdi")).sendKeys("nesto");
        driver.findElement(By.name("ime")).sendKeys("Sandra");
        driver.findElement(By.name("prezime")).sendKeys("Petrovic");
        driver.findElement(By.name("telefon")).sendKeys("+38164064061");
        driver.findElement(By.name("eposta")).sendKeys("sandra@gmail.com");
        driver.findElement(By.name("god_stud")).sendKeys("3");
        new Select(driver.findElement(By.name("diploma"))).selectByValue("0");

        WebElement fileInput = driver.findElement(By.name("slika"));
        fileInput.sendKeys("C:\\Users\\Win 11\\Desktop\\DZ1\\slike\\slika4.jpg");

        driver.findElement(By.xpath("//input[@type='submit' and @value='Registruj se']")).click();

        try {

            String text = driver.switchTo().alert().getText();
            driver.switchTo().alert().accept();

            Assert.assertTrue(text.contains("Lozinka") && text.contains("potvrda"),
                    "Nije prikazana očekivana poruka o nejednakim lozinkama!");
            System.out.println("TEST85 LOZINKE NISU ISTE - PASS");

        } catch (Throwable e) {
            System.out.println("TEST85 LOZINKE NISU ISTE - FAILED");
            Assert.fail("TEST85 LOZINKE NISU ISTE - FAILED");
        }
    }

    @Test
    public void test79RegistracijaNeuspesnaImeSadrziBrojeve() {

        driver.findElement(By.linkText("Postani korisnik")).click();
        Assert.assertTrue(driver.getCurrentUrl().contains("registracija.php"));

        driver.findElement(By.cssSelector("input[type='radio'][value='student']")).click();
        driver.findElement(By.xpath("//input[@type='submit' and @value='Zapocni registraciju']")).click();
        Assert.assertTrue(driver.getCurrentUrl().contains("reg_student.php"));

        driver.findElement(By.name("k_ime")).sendKeys("maki123");
        driver.findElement(By.name("lozinka")).sendKeys("Maja123*");
        driver.findElement(By.name("lozinka_potvrdi")).sendKeys("Maja123*");
        driver.findElement(By.name("ime")).sendKeys("123");
        driver.findElement(By.name("prezime")).sendKeys("Špirto");
        driver.findElement(By.name("telefon")).sendKeys("+381645694076");
        driver.findElement(By.name("eposta")).sendKeys("majaspirto@gmail.com");
        driver.findElement(By.name("god_stud")).sendKeys("3");
        new Select(driver.findElement(By.name("diploma"))).selectByValue("0");

        WebElement fileInput = driver.findElement(By.name("slika"));
        fileInput.sendKeys("C:\\Users\\Win 11\\Desktop\\DZ1\\slike\\slika4.jpg");

        driver.findElement(By.xpath("//input[@type='submit' and @value='Registruj se']")).click();

        try {

            String text = driver.switchTo().alert().getText();
            driver.switchTo().alert().accept();

            Assert.assertTrue(text.contains("Ime ne sme da ima karaktere koji nisu slova"),
                    "Nije prikazana očekivana poruka o neispravnom imenu!");
            System.out.println("TEST79 IME SADRZI BROJEVE - PASS");

        } catch (Throwable e) {
            System.out.println("TEST79 IME SADRZI BROJEVE - FAILED");
            Assert.fail("TEST79 IME SADRZI BROJEVE - FAILED");
        }
    }

    @Test
    public void test80RegistracijaNeuspesnaPrezimeSadrziBrojeve() {

        driver.findElement(By.linkText("Postani korisnik")).click();
        Assert.assertTrue(driver.getCurrentUrl().contains("registracija.php"));

        driver.findElement(By.cssSelector("input[type='radio'][value='student']")).click();
        driver.findElement(By.xpath("//input[@type='submit' and @value='Zapocni registraciju']")).click();
        Assert.assertTrue(driver.getCurrentUrl().contains("reg_student.php"));

        driver.findElement(By.name("k_ime")).sendKeys("majaspirto");
        driver.findElement(By.name("lozinka")).sendKeys("Maja123*");
        driver.findElement(By.name("lozinka_potvrdi")).sendKeys("Maja123*");
        driver.findElement(By.name("ime")).sendKeys("Maja");
        driver.findElement(By.name("prezime")).sendKeys("123");
        driver.findElement(By.name("telefon")).sendKeys("+381645694076");
        driver.findElement(By.name("eposta")).sendKeys("majaspirto@gmail.com");
        driver.findElement(By.name("god_stud")).sendKeys("3");
        new Select(driver.findElement(By.name("diploma"))).selectByValue("0");

        WebElement fileInput = driver.findElement(By.name("slika"));
        fileInput.sendKeys("C:\\Users\\Win 11\\Desktop\\DZ1\\slike\\slika4.jpg");

        driver.findElement(By.xpath("//input[@type='submit' and @value='Registruj se']")).click();

        try {

            String text = driver.switchTo().alert().getText();
            driver.switchTo().alert().accept();

            Assert.assertTrue(text.contains("Prezime ne sme da ima karaktere koji nisu slova"),
                    "Nije prikazana očekivana poruka o neispravnom prezimenu!");
            System.out.println("TEST80 PREZIME SADRZI BROJEVE - PASS");

        } catch (Throwable e) {
            System.out.println("TEST80 PREZIME SADRZI BROJEVE - FAILED");
            Assert.fail("TEST80 PREZIME SADRZI BROJEVE - FAILED");
        }
    }

    @AfterMethod
    public void returnToHomePage() {
        if (!driver.getCurrentUrl().equals(baseUrl)) {
            driver.get(baseUrl);
        }
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
