package com.example.examfinal.service.impl;

import com.example.examfinal.model.binding.PlaceAddBindingModel;
import com.example.examfinal.model.entity.PlaceEntity;
import com.example.examfinal.model.entity.UserEntity;
import com.example.examfinal.model.entity.UserRoleEntity;
import com.example.examfinal.model.entity.enums.NameType;
import com.example.examfinal.model.entity.enums.RatingEnum;
import com.example.examfinal.model.entity.enums.UserRoleEnum;
import com.example.examfinal.model.service.PlaceAddServiceModel;
import com.example.examfinal.model.service.PlaceUpdateServiceModel;
import com.example.examfinal.model.view.PlaceDetailsViewModel;
import com.example.examfinal.model.view.PlaceViewModel;
import com.example.examfinal.repository.CityRepository;
import com.example.examfinal.repository.PlaceRepository;
import com.example.examfinal.repository.UserRepository;
import com.example.examfinal.service.PlaceService;
import com.example.examfinal.service.exceptions.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PlaceServiceImpl implements PlaceService {

    private final PlaceRepository placeRepository;
    private final CityRepository cityRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public PlaceServiceImpl(PlaceRepository placeRepository, CityRepository cityRepository, UserRepository userRepository, ModelMapper modelMapper) {
        this.placeRepository = placeRepository;
        this.cityRepository = cityRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    @Override
    public List<PlaceViewModel> getAllPlace() {
        return placeRepository.
                findAll().
                stream().
                map(this::map).
                collect(Collectors.toList());
    }

    @Override
    public List<PlaceViewModel> getCulture() {
        return placeRepository.
                findAll().
                stream().filter(p -> p.getType().toString().equals("Културни")).
                map(this::map).
                collect(Collectors.toList());
    }

    @Override
    public List<PlaceViewModel> getNature() {
        return placeRepository.
                findAll().
                stream().filter(p -> p.getType().toString().equals("Природни")).
                map(this::map).
                collect(Collectors.toList());
    }

    @Override
    public List<PlaceViewModel> getHistory() {
        return placeRepository.
                findAll().
                stream().filter(p -> p.getType().toString().equals("Исторически")).
                map(this::map).
                collect(Collectors.toList());
    }

    @Override
    public List<PlaceViewModel> getById(Long id) {
        return placeRepository.
                findAll().
                stream().filter(p -> p.getUser().getId().toString().equals(id.toString())).
                map(this::map).
                collect(Collectors.toList());
    }


    @Override
    public List<PlaceViewModel> getReligios() {
        return placeRepository.
                findAll().
                stream().filter(p -> p.getType().toString().equals("Религиозни")).
                map(this::map).
                collect(Collectors.toList());
    }

    @Override
    public void initializePlace() {
        if (placeRepository.count() == 0) {

            PlaceEntity emona = new PlaceEntity();
            emona.setName("Емона");
            emona.setDescription("Емона е село в югоизточна България. Намира се в община Несебър, област Бургас.");
            emona.setImageUrl("https://upload.wikimedia.org/wikipedia/commons/f/fa/Emona-Evgord.jpg");
            emona.setRating(RatingEnum.СТРАХОТЕН);
            emona.setType(NameType.Природни);
            emona.setCity(cityRepository.findByName("Варна").orElse(null));
            emona.setUser(userRepository.findByUsername("admin").orElse(null));
            emona.setPrice(0.0);
            emona.setTime(1.00);
            emona.setLongitude("27.8848");
            emona.setLatitude("42.716");


            PlaceEntity ndk = new PlaceEntity();
            ndk.setName("НДК");
            ndk.setDescription("Националният дворец на културата, съкратено НДК (с наименование до 1990 г.: Народен дворец на културата „Людмила Живкова“ [1]), е национален културен център в София за конференции, изложби и специални събития, най-големият конгресен център в Югоизточна Европа[1].\n" +
                    "\n" +
                    "Основното предназначение на сградата, разполагаща с 13 зали, е за културни прояви. Най-голямата зала („Зала 1“) е с 3380 места.");
            ndk.setImageUrl("https://www.novinite.com/media/images/2017-01/photo_verybig_178274.jpg");
            ndk.setRating(RatingEnum.ДОБЪР);
            ndk.setType(NameType.Културни);
            ndk.setCity(cityRepository.findByName("София").orElse(null));
            ndk.setUser(userRepository.findByUsername("admin").orElse(null));
            ndk.setPrice(2.9);
            ndk.setTime(2.00);

            ndk.setLongitude("23.319167");
            ndk.setLatitude("42.685556");
            ndk.setNaturalSites(true);

            PlaceEntity historyMuseum = new PlaceEntity();
            historyMuseum.setName("Исторически музей");
            historyMuseum.setDescription("НИМ е създаден с разпореждане на Бюрото на Министерския съвет на НРБ през 1973 г. Първата експозиция е открита през 1984 г. в сградата на Съдебната палата в София в чест на 1300-годишнината от създаването на българската държава[1].\n" +
                    "\n" +
                    "По решение на Министерския съвет от 15.4.1998 г. на музея е предоставен Дом № 1 в държавната резиденция Бояна[2]. Новата експозиция е тържествено открита от министър-председателя Иван Костов на 29 юни 2000 г.[2]\n" +
                    "\n" +
                    "Директори на НИМ са били[1]:");
            historyMuseum.setImageUrl("https://upload.wikimedia.org/wikipedia/commons/thumb/7/79/Bulgarian_National_Museum_of_History.jpg/800px-Bulgarian_National_Museum_of_History.jpg");
            historyMuseum.setRating(RatingEnum.ДОБЪР);
            historyMuseum.setType(NameType.Исторически);
            historyMuseum.setCity(cityRepository.findByName("София").orElse(null));
            historyMuseum.setUser(userRepository.findByUsername("admin").orElse(null));
            historyMuseum.setPrice(2.9);
            historyMuseum.setTime(0.0);
            historyMuseum.setLongitude("25.321944");
            historyMuseum.setLatitude("41.748333");
            historyMuseum.setNaturalSites(true);

            PlaceEntity svAlNevski = new PlaceEntity();
            svAlNevski.setName("Свети Александър Невски");
            svAlNevski.setDescription("„Свети Александър Невски“ е православен храм-паметник в град София, България, катедрален храм на българския патриарх. Църквата е изградена в периода 1882 – 1912 година и през 1955 година е обявена за паметник на културата с национално значение. Катедралата е разположена на площад със същото име.");
            svAlNevski.setImageUrl("https://upload.wikimedia.org/wikipedia/commons/thumb/e/e7/Cathedral_Saint_Alexander_Nevsky_%2823997180108%29.jpg/800px-Cathedral_Saint_Alexander_Nevsky_%2823997180108%29.jpg");
            svAlNevski.setRating(RatingEnum.СТРАХОТЕН);
            svAlNevski.setType(NameType.Религиозни);
            svAlNevski.setCity(cityRepository.findByName("София").orElse(null));
            svAlNevski.setUser(userRepository.findByUsername("admin").orElse(null));
            svAlNevski.setPrice(0.0);
            svAlNevski.setTime(0.0);
            svAlNevski.setLongitude("23.3216");
            svAlNevski.setLatitude("42.6966");
            svAlNevski.setNaturalSites(true);

            PlaceEntity vruhShipka = new PlaceEntity();
            vruhShipka.setName("Връх Шипка");
            vruhShipka.setDescription("Свети Никола (Шипка) е връх в Стара планина с надморска височина 1326 m, разположен на изток от Шипченския проход. До 23 ноември 1951 г. върхът носи името Свети Никола, а след това до 7 октомври 1977 г. – връх Столетов.[1][2] Върхът символизира героизма и самопожертвувателните усилия за освобождението на България от Османско владичество.\n" +
                    "\n" +
                    "През 1934 г. на върха е построен Паметникът на свободата, до който водят автомобилен път и каменно стълбище. Паметникът е част от Парк-музей „Шипка“. Шипка се нарича и върхът (с височина 1232 m), разположен на север от главното било и на запад от Шипченския проход.");
            vruhShipka.setImageUrl("https://www.monitor.bg/web/files/articles/228951/gallery/thumb_1200x630_1551426864Shipka.jpg");
            vruhShipka.setRating(RatingEnum.СТРАХОТЕН);
            vruhShipka.setType(NameType.Културни);
            vruhShipka.setCity(cityRepository.findByName("Стара Загора").orElse(null));
            vruhShipka.setUser(userRepository.findByUsername("admin").orElse(null));
            vruhShipka.setPrice(0.0);
            vruhShipka.setTime(0.0);
            vruhShipka.setLatitude("42.748333");
            vruhShipka.setLongitude("25.321944");
            vruhShipka.setNaturalSites(true);

            PlaceEntity rilskiManastir = new PlaceEntity();
            rilskiManastir.setName("Рилски манастир");
            rilskiManastir.setDescription("Рилски манастир „Свети Иван Рилски“ [1] е български ставропигиален манастир, сред най-значимите културни паметници в България, символ на страната, включен в Списъка на световното наследство на ЮНЕСКО.\n" +
                    "\n" +
                    "Разположен е в Югозападна България, област Кюстендил, община Рила. Основан е през Х век от св. Йоан Рилски Чудотворец[2] по горното течение на Рилска река.");
            rilskiManastir.setImageUrl("https://trud.bg/public/images/articles/2018-08/07-1-2_4858737883489721362_original.jpg");
            rilskiManastir.setRating(RatingEnum.СТРАХОТЕН);
            rilskiManastir.setType(NameType.Религиозни);
            rilskiManastir.setCity(cityRepository.findByName("Кюстендил").orElse(null));
            rilskiManastir.setUser(userRepository.findByUsername("admin").orElse(null));
            rilskiManastir.setPrice(0.0);
            rilskiManastir.setTime(0.0);
            rilskiManastir.setLatitude("42.1335");
            rilskiManastir.setLongitude("23.3402");
            rilskiManastir.setNaturalSites(true);

            PlaceEntity belogradchiskiSkali = new PlaceEntity();
            belogradchiskiSkali.setName("Белоградчиски скали");
            belogradchiskiSkali.setDescription("Белоградчѝшките скали са скални форми в Западния Предбалкан, в южното подножие на Белоградчишкия венец, около Белоградчик и селата Боровица, Чифлик и Праужда в област Видин и село Белотинци, област Монтана. [1] Обявени са за природна забележителност през 1949 г. Включени са в списъка на Стоте национални туристически обекта. Включени са в Червената книга на България като уязвимо природно местообитание.[2]");
            belogradchiskiSkali.setImageUrl("https://imgrabo.com/pics/guide/900x600/20160129125936_67167.jpg");
            belogradchiskiSkali.setRating(RatingEnum.СТРАХОТЕН);
            belogradchiskiSkali.setType(NameType.Природни);
            belogradchiskiSkali.setCity(cityRepository.findByName("Видин").orElse(null));
            belogradchiskiSkali.setUser(userRepository.findByUsername("admin").orElse(null));
            belogradchiskiSkali.setPrice(0.0);
            belogradchiskiSkali.setTime(0.0);
            belogradchiskiSkali.setLatitude("43.620833");
            belogradchiskiSkali.setLongitude("22.685");
            belogradchiskiSkali.setNaturalSites(true);
            placeRepository.saveAll(List.of(emona, ndk, historyMuseum, svAlNevski, vruhShipka, rilskiManastir, belogradchiskiSkali
            ));
        }
    }

    @Override
    public void deletePlace(Long id) {
        placeRepository.deleteById(id);
    }

    @Override
    public boolean isOwner(String userName, Long id) {
        Optional<PlaceEntity> placeOpt = placeRepository.findById(id);
        Optional<UserEntity> caller = userRepository.
                findByUsername(userName);

        if (placeOpt.isEmpty() || caller.isEmpty()) {
            return false;
        } else {
            PlaceEntity place = placeOpt.get();
            return isAdmin(caller.get()) ||
                    place.getUser().getUsername().equals(userName);
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
    public void updatePlace(PlaceUpdateServiceModel placeModel) {

        PlaceEntity place =
                placeRepository.findById(placeModel.getId()).orElseThrow(() ->
                        new ObjectNotFoundException("Place with id " + placeModel.getId()
                                + " not found!"));

        place.setName(placeModel.getName());
        place.setDescription(placeModel.getDescription());
        place.setType(placeModel.getType());
        place.setImageUrl(placeModel.getImageUrl());
        place.setRating(placeModel.getRating());
        place.setPrice(placeModel.getPrice());
        place.setModified(Instant.now());
        place.setNaturalSites(placeModel.getNaturalSites());
        place.setLatitude(placeModel.getLatitude());
        place.setLongitude(placeModel.getLongitude());
        placeRepository.save(place);
    }

    @Override
    public PlaceAddServiceModel addPlace(PlaceAddBindingModel placeAddBindModel, String ownerId) {
        UserEntity userEntity = userRepository.findByUsername(ownerId).orElseThrow();
        PlaceAddServiceModel placeAddServiceModel = modelMapper.map(placeAddBindModel,
                PlaceAddServiceModel.class);
        PlaceEntity newPlace = modelMapper.map(placeAddBindModel, PlaceEntity.class);
        newPlace.setCreated(Instant.now());
        newPlace.setUser(userEntity);

        PlaceEntity savedPlace = placeRepository.save(newPlace);
        return modelMapper.map(savedPlace, PlaceAddServiceModel.class);
    }

    @Override
    public void deletePlacesInOneCity(Long cityId) {
        placeRepository.findAll().stream().filter(placeEntity -> !placeEntity.getCity().getId().equals(cityId));
    }

    @Transactional
    @Override
    public PlaceDetailsViewModel findById(Long id) {
        PlaceDetailsViewModel placeDetailsViewModel = this.placeRepository.findById(id).map(this::mapDetailsView).get();
        return placeDetailsViewModel;
    }


    private PlaceViewModel map(PlaceEntity placeEntity) {
        PlaceViewModel placeViewModel = this.modelMapper.map(placeEntity, PlaceViewModel.class);
        return placeViewModel;
    }

    private PlaceDetailsViewModel mapDetailsView(PlaceEntity place) {
        PlaceDetailsViewModel placeDetailsViewModel = this.modelMapper.map(place, PlaceDetailsViewModel.class);

        return placeDetailsViewModel;
    }
}
