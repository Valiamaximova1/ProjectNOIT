package com.example.examfinal.service.impl;


import com.example.examfinal.model.binding.CityAddBindModel;
import com.example.examfinal.model.entity.CityEntity;
import com.example.examfinal.model.entity.UserEntity;
import com.example.examfinal.model.entity.UserRoleEntity;
import com.example.examfinal.model.entity.enums.AreaOfBg;
import com.example.examfinal.model.entity.enums.UserRoleEnum;
import com.example.examfinal.model.service.CityAddServiceModel;
import com.example.examfinal.model.service.CityUpdateServiceModel;
import com.example.examfinal.model.view.CityDetailsView;
import com.example.examfinal.model.view.CityView;
import com.example.examfinal.repository.CityRepository;
import com.example.examfinal.repository.PlaceRepository;
import com.example.examfinal.repository.UserRepository;
import com.example.examfinal.service.CityService;
import com.example.examfinal.service.exceptions.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class  CityServiceImpl implements CityService {
    private final CityRepository cityRepository;
    private final ModelMapper modelMapper;
    private final PlaceRepository placeRepository;
    private final UserRepository userRepository;

    public CityServiceImpl(CityRepository cityRepository, ModelMapper modelMapper, PlaceRepository placeRepository, UserRepository userRepository) {
        this.cityRepository = cityRepository;
        this.modelMapper = modelMapper;
        this.placeRepository = placeRepository;
        this.userRepository = userRepository;
    }


    @Override
    public void initializeCities() {
        if (cityRepository.count() == 0) {
            CityEntity sofia = new CityEntity();
            sofia.setName("София");
            sofia.setImageUrl("https://webnews.bg/uploads/images/03/5503/375503/768x432.jpg?_=1535560681");
            sofia.setPopulation(1950);
            sofia.setMayor("Йорданка Фандъкова");
            sofia.setAreaOfBg(AreaOfBg.ЗАПАДНА);
            sofia.setDescription("Со̀фия е столицата и най-големият град на България. Тя е на 14-о място по брой жители в Европейския съюз. Според ГРАО населението по настоящ адрес е 1 277 411 души, а по постоянен адрес е 1 374 742 души (към 15 декември 2021 г.).[2] Според резултатите от преброяването през 2011 г. населението на града е 1 291 591,[3] което представлява 17,5% от населението на България. София е разположена в централната част на Западна България, в Софийската котловина и е заобиколена от 5 планини: Витоша и Плана от юг, Софийската планина (част от Стара планина) от север, Люлин от запад, и Лозенската планина (част от Ихтиманска Средна гора) от изток. Това я прави четвъртата по височина столица в Европа. Изградена е върху четирите тераси на река Искър и нейните притоци: Перловска и Владайска (Елешница). В централната градска част, както и в кварталите Овча купел, Княжево, Горна баня и Панчарево, има минерални извори. Климатът на София е умерено континентален.\n" +
                    "\n" +
                    "София е основен административен, индустриален, транспортен, културен и университетски център на страната, като в нея е съсредоточено 1/6 от промишленото производство на България. Тук се намират също така Българската академия на науките, много университети, театри, кина, както и Националната художествена галерия, археологически, исторически, природонаучни и други музеи. На много места в центъра на града са запазени видими археологически паметници от римско време. ");
            sofia.setUser(userRepository.findByUsername("admin").orElse(null));

            CityEntity blagoevgrad = new CityEntity();
            blagoevgrad.setName("Благоевград");
            blagoevgrad.setImageUrl("https://upload.wikimedia.org/wikipedia/commons/thumb/3/3b/Blagoevgrad.Downtown.jpg/800px-Blagoevgrad.Downtown.jpg");
            blagoevgrad.setPopulation(73463);
            blagoevgrad.setMayor("Илко Стоянов");
            blagoevgrad.setAreaOfBg(AreaOfBg.ЮЖНА);
            blagoevgrad.setDescription("Благо̀евград (до 1950 г. Го̀рна Джумая̀) е град в България. Той е икономически, културен и образователен център на Югозападна България и административен център на едноименните област и община. Към 15 декември 2021 година градът е 14-и по население в България с 73 463 жители.");
            blagoevgrad.setUser(userRepository.findByUsername("admin").orElse(null));

            CityEntity burgas = new CityEntity();
            burgas.setName("Бургас");
            burgas.setImageUrl("https://www.burgas.bg/uploads/posts/2021/burgas-bulgaria-1800x1000.jpg");
            burgas.setPopulation(210720);
            burgas.setMayor("Димитър Николов");
            burgas.setAreaOfBg(AreaOfBg.ИЗТОЧНА);
            burgas.setDescription("Бурга̀с е най-големият град в Югоизточна България и вторият по големина на българското Черноморие, а със своето землище от 253,644 km², той е вторият град по площ в България (след София). Според последното преброяване на ГРАО към 15 декември 2021 г. населението е 210 720 жители и така продължава да е четвъртият по население град в страната (след София, Пловдив и Варна).[2] Бургас е най-важният културен, стопански, транспортен, управленски, туристически и просветен център в Югоизточна България и е с национално значение. Градът е административно средище на едноименната община и област, както и седалище на регионални и национални институции.");
            burgas.setUser(userRepository.findByUsername("admin").orElse(null));

            CityEntity city1 = new CityEntity();
            city1.setName("Варна");
            city1.setImageUrl("https://img.capital.bg/shimg/zx952y526_3916897.jpg");
            city1.setPopulation(347924 );
            city1.setMayor("Иван Портних");
            city1.setAreaOfBg(AreaOfBg.ИЗТОЧНА);
            city1.setDescription("Ва̀рна е най-големият град в Североизточна България, разположен по бреговете на Черно море и Варненското езеро и е административен център на едноименните община и област. Той е най-големият град в Северна България и по българското Черноморие.");
            city1.setUser(userRepository.findByUsername("admin").orElse(null));

            CityEntity velikoTarnovo = new CityEntity();
            velikoTarnovo.setName("Велико Търново");
            velikoTarnovo.setImageUrl("https://www.veliko-tarnovo.bg/media//filer/_thumbnails/2021/04/13/veliko-tarnovo-town-view.jpg__660x379_q85_crop_subsampling-2.jpg");
            velikoTarnovo.setPopulation(66550 );
            velikoTarnovo.setMayor("Даниел Панов");
            velikoTarnovo.setAreaOfBg(AreaOfBg.СЕВЕРНА);
            velikoTarnovo.setDescription("Велико Тъ̀рново е град, намиращ се в Северна България, център на едноименната област, както и на Северен централен регион. Градът е столица на Втората българска държава и на Княжество България в периода 1878 – 1879 г. През Средновековието носи името Търновград, като постепенно става известен с наименованието Търново. На 27 юли 1965 г. пред него е добавено „Велико“[2] в чест на предишната слава на града. Благодарение на богатото си културно-историческо наследство Велико Търново е важен туристически център.");
            velikoTarnovo.setUser(userRepository.findByUsername("admin").orElse(null));

            CityEntity vidin = new CityEntity();
            vidin.setName("Видин");
            vidin.setImageUrl("https://feelbulgaria.net/wp-content/uploads/2021/07/bulgaria-987552_1280.jpg");
            vidin.setPopulation(45926 );
            vidin.setMayor("Цветан Ценков");
            vidin.setAreaOfBg(AreaOfBg.СЕВЕРНА);
            vidin.setDescription("Вѝдин е град в Северозападна България, разположен на десния бряг на река Дунав. Той е административен и стопански център на едноименните община Видин и област Видин.");
            vidin.setUser(userRepository.findByUsername("admin").orElse(null));

            CityEntity vratsa = new CityEntity();
            vratsa.setName("Враца");
            vratsa.setImageUrl("https://vrak.lex.bg/userfiles//image/plbotev2web(1).jpg");
            vratsa.setPopulation(57028 );
            vratsa.setMayor("Калин Каменов");
            vratsa.setAreaOfBg(AreaOfBg.СЕВЕРНА);
            vratsa.setDescription("Враца (стара паралелна форма Вратца) е град в Северозападна България с население към 2019 г. 51 674 души.[2][3][4] Административен и стопански център на едноименните община Враца и област Враца. Намира се на около 112 km северно от София, 125 km югоизточно от Видин и 40 km югоизточно от Монтана.");
            vratsa.setUser(userRepository.findByUsername("admin").orElse(null));

            CityEntity gabrovo = new CityEntity();
            gabrovo.setName("Габрово");
            gabrovo.setImageUrl("https://trud.bg/public/images/articles/2020-05/gabrovo_3862942016524140613_big.jpg");
            gabrovo.setPopulation(52974 );
            gabrovo.setMayor("Таня Христова");
            gabrovo.setAreaOfBg(AreaOfBg.ЦЕНТРАЛНА);
            gabrovo.setDescription("Га̀брово е град в централна България, административен и стопански център на едноименните община Габрово и област Габрово. Разположен е по поречието на река Янтра в северното подножие на Шипченския дял на Стара планина. В непосредствена близост до него, в местността Узана, се намира географският център на България.\n" +
                    "\n" +
                    "По последни данни на НСИ към 15 декември 2021 г. населението на Габрово е 52 974 души.");
            gabrovo.setUser(userRepository.findByUsername("admin").orElse(null));


            CityEntity dobrich = new CityEntity();
            dobrich.setName("Добрич");
            dobrich.setImageUrl("https://pronewsdobrich.bg/wp-content/uploads/2020/11/%D0%9D%D0%B0%D0%B4%D0%BF%D0%B8%D1%81-%D0%94%D0%9E%D0%91%D0%A0%D0%98%D0%A7-%D0%B1%D1%83%D0%BA%D0%B2%D0%B82.jpg");
            dobrich.setPopulation(90419 );
            dobrich.setMayor("Йордан Йорданов");
            dobrich.setAreaOfBg(AreaOfBg.ИЗТОЧНА);
            dobrich.setDescription("До̀брич (Толбухин в периода 1949 – 1990 г., на името на съветския маршал Фьодор Толбухин) е град в Североизточна България, административен и стопански център на област Добрич, община Добрич и община Добричка. Разположен е в южната част на историко-географската област Добруджа. Градът е девети по големина в страната с население от 80 936 жители към 31.12.2020 г. по данни от НСИ (91 453 жители по настоящ адрес към 15.04.2021 г. според ЕСГРАОН).");
            dobrich.setUser(userRepository.findByUsername("admin").orElse(null));

            CityEntity kurjali = new CityEntity();
            kurjali.setName("Кърджали");
            kurjali.setImageUrl("https://bia-bg.com/uploads/images/Gradove/kurdjali.jpg");
            kurjali.setPopulation(51242  );
            kurjali.setMayor("Хасан Азис");
            kurjali.setAreaOfBg(AreaOfBg.ЮЖНА);
            kurjali.setDescription("Кърджали (на турски: Kırcaali; на гръцки: Κάρντζαλι) е град в Южна България. Той е център на област Кърджали и на община Кърджали. Намира се на 47 km от град Хасково, на 84 km от град Смолян, на 95 km от град Пловдив, на 111 km от град Стара Загора, на 243 km от София, на 78 km от Гюмюрджина и на 383 km от Истанбул.");
            kurjali.setUser(userRepository.findByUsername("admin").orElse(null));

            CityEntity kustendil = new CityEntity();
            kustendil.setName("Кюстендил");
            kustendil.setImageUrl("http://patekite.info/wp-content/uploads/2018/10/kyustendil-panorama.jpeg");
            kustendil.setPopulation(44787  );
            kustendil.setMayor("Петър Паунов");
            kustendil.setAreaOfBg(AreaOfBg.ЗАПАДНА);
            kustendil.setDescription("Кюстендѝл (в Средновековието: Велбъжд, в Античността: Пауталия) е град в Югозападна България, административен център на едноименната област и община.\n" +
                    "\n" +
                    "Намира се в близост до границата с Република Северна Македония и с Република Сърбия. Балнеоложки и туристически център с национално и международно значение, археологически и архитектурен резерват, изходен пункт за туризъм и ски спорт в планината Осогово.");
            kustendil.setUser(userRepository.findByUsername("admin").orElse(null));


            CityEntity lovech = new CityEntity();
            lovech.setName("Ловеч");
            lovech.setImageUrl("https://geograf.bg/sites/default/files/article/201507/1802.jpg");
            lovech.setPopulation(33195  );
            lovech.setMayor("Корнелия Маринова");
            lovech.setAreaOfBg(AreaOfBg.ЦЕНТРАЛНА);
            lovech.setDescription("Ло̀веч е град в Централна Северна България. По време на османската власт е наричан „Алтън Ловеч“ – „Златният Ловеч“. Днес Ловеч е административен и стопански център на едноименните община Ловеч и област Ловеч. Населението на града по данни на НСИ към 31.12.2019 г. е 34 056 жители.");
            lovech.setUser(userRepository.findByUsername("admin").orElse(null));

            CityEntity montana = new CityEntity();
            montana.setName("Монтана");
            montana.setImageUrl("https://darikradio.bg/media/323/montana-11.m.m.jpg");
            montana.setPopulation(40295  );
            montana.setMayor("Златко Живков");
            montana.setAreaOfBg(AreaOfBg.ЗАПАДНА);
            montana.setDescription("Монта̀на е областен град в Северозападна България, административен и стопански център на едноименните община Монтана и област Монтана.");
            montana.setUser(userRepository.findByUsername("admin").orElse(null));

            CityEntity pazarjik = new CityEntity();
            pazarjik.setName("Пазарджик");
            pazarjik.setImageUrl("https://www.plovdiv-press.bg/wp-content/uploads/2020/01/%D0%BF%D0%B0%D0%B7%D0%B0%D1%80%D0%B4%D0%B6%D0%B8%D0%BA.jpg");
            pazarjik.setPopulation(74785  );
            pazarjik.setMayor("Тодор Попов");
            pazarjik.setAreaOfBg(AreaOfBg.ЮЖНА);
            pazarjik.setDescription("Па̀зарджик е областен град в Южна България, административен център на община Пазарджик и на област Пазарджик. Намира се на 112 km югоизточно от столицата София, на 37 km западно от Пловдив, на 404 km от Варна и на 288 km от Бургас. Лежи на двата бряга на река Марица в западната част на Горнотракийската низина и е вторият най-важен културен и административен център в Южна Централна България. Последното преброяване на ГРАО към 15 декември 2021 г. оценява населението на града на 74 785 жители.");
            pazarjik.setUser(userRepository.findByUsername("admin").orElse(null));

            CityEntity pernik = new CityEntity();
            pernik.setName("Перник");
            pernik.setImageUrl("https://www.periscop.bg/wp-content/uploads/2019/07/2258c545f0ab5a7392e60a0468ad6082.jpg");
            pernik.setPopulation(74553  );
            pernik.setMayor("Станислав Владимиров");
            pernik.setAreaOfBg(AreaOfBg.ЗАПАДНА);
            pernik.setDescription("Перник е град в Югозападна България, административен център на област Перник и община Перник. По последни данни на НСИ към 31 декември 2018 г. населението му наброява 73 111 души, което го прави най-многочисления град в Западна България след столицата София и го нарежда на 11-о място в страната.");
            pernik.setUser(userRepository.findByUsername("admin").orElse(null));

            CityEntity pleven = new CityEntity();
            pleven.setName("Плевен");
            pleven.setImageUrl("https://static.bnr.bg/gallery/cr/41abe6614549b6bccbaac06d5829ba28.jpg");
            pleven.setPopulation(101299  );
            pleven.setMayor("Георг Спартански");
            pleven.setAreaOfBg(AreaOfBg.СЕВЕРНА);
            pleven.setDescription("Плѐвен (изписване до 1945 година: Плѣвенъ) е град в Централна Северна България. Той е административен център на едноименните община Плевен и област Плевен, както и един от важните културни, образователни, икономически и транспортни центрове в страната. Плевен и неговата околност имат корени от дълбока древност и практически непрекъснато хилядолетно развитие.");
            pleven.setUser(userRepository.findByUsername("admin").orElse(null));

            CityEntity plovdiv = new CityEntity();
            plovdiv.setName("Пловдив");
            plovdiv.setImageUrl("https://www.plovdiv.bg/wp-content/uploads/2021/06/piro-slider.jpg");
            plovdiv.setPopulation(366511  );
            plovdiv.setMayor("Здравко Димитров");
            plovdiv.setAreaOfBg(AreaOfBg.ЮЖНА);
            plovdiv.setDescription("Пло̀вдив е вторият по големина град в България с население от 366 511 души по настоящ адрес, към 15 декември 2021 г.[2] Намира се в западната част на Горнотракийската низина, на двата бряга на река Марица. Отстои на 15 km северно от Родопите и на 50 km южно от Стара планина. Градът е застроен в подножията на шест сиенитни хълма, поради което често е наричан „Градът под тепетата“. Пловдив е управленски център на област Пловдив, община Пловдив, община Марица, община Родопи и е най-голямото стопанско ядро на Южния централен район.");
            plovdiv.setUser(userRepository.findByUsername("admin").orElse(null));

            CityEntity razgrad = new CityEntity();
            razgrad.setName("Разград");
            razgrad.setImageUrl("https://razgradnews.net/wp-content/uploads/bfi_thumb/razgrad-6oqraxkt8vxoml0eu9gwlo3kfmh41azrjuch3bn310c.jpg");
            razgrad.setPopulation(33034  );
            razgrad.setMayor("Денчо Бояджиев");
            razgrad.setAreaOfBg(AreaOfBg.СЕВЕРНА);
            razgrad.setDescription("Ра̀зград е град, разположен в Североизточна България. Той е административен център на община Разград, област Разград. Разположен е в южната част на историко-географска област Лудогорие.");
            razgrad.setUser(userRepository.findByUsername("admin").orElse(null));

            CityEntity ruse = new CityEntity();
            ruse.setName("Русе");
            ruse.setImageUrl("https://static.bnr.bg/gallery/cr/a286c19bc98f6c3c19ffbbcb307452d7.jpg");
            ruse.setPopulation(143931  );
            ruse.setMayor("Пенчо Милков");
            ruse.setAreaOfBg(AreaOfBg.СЕВЕРНА);
            ruse.setDescription("Ру̀се е най-големият български град по поречието на река Дунав и петият град по големина в страната, след София, Пловдив, Варна и Бургас. Разположен е на североизточната граница на страната с Румъния. Градът е административен център на община Русе и област Русе, както и икономически, транспортен, културен и образователен център от регионално и национално значение");
            ruse.setUser(userRepository.findByUsername("admin").orElse(null));

            CityEntity silistra = new CityEntity();
            silistra.setName("Силистра");
            silistra.setImageUrl("https://www.kvorum-silistra.info/images/news/14290.jpg");
            silistra.setPopulation(34473  );
            silistra.setMayor("Юлиан Найденов");
            silistra.setAreaOfBg(AreaOfBg.СЕВЕРНА);
            silistra.setDescription("Силистра е пристанищен град на река Дунав в Североизточна България. Той е административен и стопански център на едноименните община Силистра и област Силистра. Силистра е 28-и по население в България. Силистра е град с богата история, като забележителности тук се открояват късноантична римска гробница и турската крепост Меджиди Табия.");
            silistra.setUser(userRepository.findByUsername("admin").orElse(null));

            CityEntity sliven = new CityEntity();
            sliven.setName("Сливен");
            sliven.setImageUrl("https://mun.sliven.bg/uploads/9BFF4C77AC22A4E082066C7C32C20A80.jpg");
            sliven.setPopulation(89540  );
            sliven.setMayor("Стефан Радев");
            sliven.setAreaOfBg(AreaOfBg.ИЗТОЧНА);
            sliven.setDescription("Сливен (изписване до 1945 година: Сливенъ) е град в Югоизточна България. Той е осмият по големина в страната и е административен център на община Сливен. Сливен е известен като Градът на стоте войводи, свързани с хайдушкото движение.\n" +
                    "\n" +
                    "По данни на ГРАО към 15 септември 2021 г. населението на Сливен е 90 005 души.");
            sliven.setUser(userRepository.findByUsername("admin").orElse(null));

            CityEntity smolqn = new CityEntity();
            smolqn.setName("Смолян");
            smolqn.setImageUrl("https://www.smolyan.bg/media/content_files/file/image/%D0%A2%D0%A3%D0%A0%D0%98%D0%97%D0%AA%D0%9C/0_%20%D0%97%D0%90%D0%93%D0%9B%D0%90%D0%92%D0%9D%D0%90/3.jpg");
            smolqn.setPopulation(28541  );
            smolqn.setMayor("Николай Мелемов");
            smolqn.setAreaOfBg(AreaOfBg.ЮЖНА);
            smolqn.setDescription("Смо̀лян е град в Южна България. Намира се в Переликско-Преспанския дял на Западните Родопи при голяма надморска височина – 1035 m. Градът е образуван на 18 юни 1960 г. от двата града Смолян и Устово, и селата Райково и Езерово.[2] Той е административен център на община Смолян и област Смолян.\n" +
                    "По данни на НСИ към 31.12.2019 г. населението му е 27 092 души, което го прави най-малкия областен град в България. Смолян е един от най-дългите градове в България – близо 25 km, което се дължи на линейното му застрояване по поречието на река Черна.");
            smolqn.setUser(userRepository.findByUsername("admin").orElse(null));


            CityEntity sofiaOblast = new CityEntity();
            sofiaOblast.setName("София Област");
            sofiaOblast.setImageUrl("http://asso-bg.net/sofiqoblast.gif");
            sofiaOblast.setPopulation(1291591   );
            sofiaOblast.setMayor("-");
            sofiaOblast.setAreaOfBg(AreaOfBg.ЗАПАДНА);
            sofiaOblast.setDescription("Област София[1], наричана синонимно също Софийска област[2] и област София – град или София – столица, както и Столична област, е област на България.\n" +
                    "\n" +
                    "Заема площ от 1 348,9 km². Населението ѝ възлиза на 1 480 830 души (2021)[3], което я прави най-населената и най-гъсто населената област в България. През 2011, мнозинството (1 202 761 души или 93,4 %) от жителите на областта живеят в гр. София.\n" +
                    "\n" +
                    "На запад, север, изток, югоизток граничи със Софийска област, а на югозапад – с област Перник.");
            sofiaOblast.setUser(userRepository.findByUsername("admin").orElse(null));

            CityEntity staraZagora = new CityEntity();
            staraZagora.setName("Стара Загора");
            staraZagora.setImageUrl("https://img-cdn.dnes.bg/d/images/photos/0463/0000463043-article2.jpg");
            staraZagora.setPopulation(136475   );
            staraZagora.setMayor("Живко Тодоров ");
            staraZagora.setAreaOfBg(AreaOfBg.ЮЖНА);
            staraZagora.setDescription("Ста̀ра Заго̀ра е град в Южна България, един от основните икономически центрове в страната, както и основен транспортен възел на Южна България. Той е център на едноименните община, област и регионална асоциация на общините РАО Тракия. Градът е шестият по големина в страната с население от 136 475 души по данни на ГРАО към 15.12.2021 г.[3] и образува петата по големина градска агломерация в България с население от 213 444 жители (към септември 2007 г.),[4] както и център на 5-ата по големина област в България с население към 31 декември 2016 г. от 321 377 души.[5] Стабилното икономическо развитие на региона му отрежда второ място по БВП[6] на глава от населението в страната.");
            staraZagora.setUser(userRepository.findByUsername("admin").orElse(null));

            CityEntity turgovishte = new CityEntity();
            turgovishte.setName("Търговище");
            turgovishte.setImageUrl("https://trud.bg/public/images/articles/2020-11/TG_dron_panorama_299258840450956664_original.jpg");
            turgovishte.setPopulation(39874);
            turgovishte.setMayor("Дарин Димитров ");
            turgovishte.setAreaOfBg(AreaOfBg.ИЗТОЧНА);
            turgovishte.setDescription("Търго̀вище (до 1934 година – Ескѝ Джумая̀)[2] е областен град в Североизточна България, административен и стопански център на едноименната община Търговище и област Търговище. Наричан е още града на панаирите.[източник? (Поискан преди 18 дни)] Населението на града към 31 декември 2019 г. е 35 344 жители. Търговище е 25-тия най-голям град в България.");
            turgovishte.setUser(userRepository.findByUsername("admin").orElse(null));

            CityEntity haskovo = new CityEntity();
            haskovo.setName("Хасково");
            haskovo.setImageUrl("https://demokrati.bg/wp-content/uploads/2019/09/haskovo.jpg");
            haskovo.setPopulation(72016);
            haskovo.setMayor("Станислав Дечев");
            haskovo.setAreaOfBg(AreaOfBg.ЮЖНА);
            haskovo.setDescription("Ха̀сково е град в централна Южна България. Той е административен център на община Хасково и област Хасково. Според последните данни на ГРАО, към 15 декември 2021 г.,[2] Хасково е с население 72 016 души и е 12-ият по големина град в страната.");
            haskovo.setUser(userRepository.findByUsername("admin").orElse(null));

            CityEntity shumen = new CityEntity();
            shumen.setName("Шумен");
            shumen.setImageUrl("https://img.capital.bg/shimg/zx952y526_4220352.jpg");
            shumen.setPopulation(82497);
            shumen.setMayor("Любомир Христов");
            shumen.setAreaOfBg(AreaOfBg.ИЗТОЧНА);
            shumen.setDescription("Шу̀мен (Коларовград в периода 1950 – 1965 г.) е град в Североизточна България, административен и стопански център на едноименните община Шумен и област Шумен. Градът е десети по големина в страната с население от 75 442 жители към 31 декември 2019 г. според НСИ (85 504 жители по настоящ адрес към 15 декември 2019 г. според ЕСГРАОН).");
            shumen.setUser(userRepository.findByUsername("admin").orElse(null));

            CityEntity qmbol = new CityEntity();
            qmbol.setName("Ямбол");
            qmbol.setImageUrl("https://gradat.bg/sites/default/files/styles/page_article_scale/public/mainimages/o_3054104.jpg?itok=ia4PHrVH");
            qmbol.setPopulation(70782);
            qmbol.setMayor("Валентин Ревански");
            qmbol.setAreaOfBg(AreaOfBg.ИЗТОЧНА);
            qmbol.setDescription("Я̀мбол е град в Югоизточна България. Той е административен център на област Ямбол, както и център и единствено населено място на община Ямбол. Според последното преброяване на НСИ към 31 декември 2018 г. населението на града е 68 074 души.");
            qmbol.setUser(userRepository.findByUsername("admin").orElse(null));

            cityRepository.saveAll(List.of( blagoevgrad, burgas, city1, velikoTarnovo, vidin, vratsa, gabrovo,
                    dobrich, kurjali, kustendil, lovech, montana, pazarjik, pernik, pleven, plovdiv, razgrad, ruse,
                    silistra, sliven, smolqn, sofiaOblast, sofia, staraZagora, turgovishte, haskovo, shumen, qmbol));
        }
    }


    @Override
    public List<CityView> getAllCities() {
        return cityRepository.
                findAll().
                stream().
                map(this::map).
                collect(Collectors.toList());
    }

    @Override
    public CityDetailsView findById(Long id) {
        CityDetailsView cityDetailsView = this.cityRepository.findById(id).map(this::mapDetailsView).get();
        return cityDetailsView;
    }

    @Override
    public void deleteCity(Long id) {
        cityRepository.deleteById(id);
    }

    @Override
    public boolean isOwner(String userName, Long id) {
        Optional<CityEntity> cityOpt = cityRepository.
                findById(id);
        Optional<UserEntity> caller = userRepository.
                findByUsername(userName);

        if (cityOpt.isEmpty() || caller.isEmpty()) {
            return false;
        } else {
            CityEntity cityEntity = cityOpt.get();

            return isAdmin(caller.get()) ||
                    cityEntity.getUser().getUsername().equals(userName);
        }
    }

    private boolean isAdmin(UserEntity user) {
        return user.
                getRoles().
                stream().
                map(UserRoleEntity::getRole).
                anyMatch(r -> r == UserRoleEnum.ADMIN);
    }

    @Override
    public void updateCity(CityUpdateServiceModel cityUpdate) {
        CityEntity city =
                cityRepository.findById(cityUpdate.getId()).orElseThrow(() ->
                        new ObjectNotFoundException("Offer with id " + cityUpdate.getId()
                                + " not found!"));

        city.setName(cityUpdate.getName());
        city.setDescription(cityUpdate.getDescription());
        city.setMayor(cityUpdate.getMayor());
        city.setImageUrl(cityUpdate.getImageUrl());
        city.setPopulation(cityUpdate.getPopulation());
        city.setAreaOfBg(cityUpdate.getAreaOfBg());

        cityRepository.save(city);
    }

    @Override
    public CityAddServiceModel addCity(CityAddBindModel cityAddBindModel, String ownerId) {
        UserEntity userEntity = userRepository.findByUsername(ownerId).orElseThrow();
        CityAddServiceModel cityAddServiceModel = modelMapper.map(cityAddBindModel,
                CityAddServiceModel.class);
        CityEntity newCity = modelMapper.map(cityAddBindModel, CityEntity.class);
        newCity.setCreated(Instant.now());
        newCity.setUser(userEntity);


        CityEntity savedCity = cityRepository.save(newCity);
        return modelMapper.map(savedCity, CityAddServiceModel.class);
    }


    private CityView map(CityEntity cityEntity) {
        CityView cityView = this.modelMapper.map(cityEntity, CityView.class);

        return cityView;
    }

    private CityDetailsView mapDetailsView(CityEntity city) {
        CityDetailsView cityDetailsView = this.modelMapper.map(city, CityDetailsView.class);
        return cityDetailsView;
    }
}
