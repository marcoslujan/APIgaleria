package com.dam.APIgaleria.data;

import com.dam.APIgaleria.exception.DataException;
import com.dam.APIgaleria.model.Alienigena;
import com.dam.APIgaleria.repository.AlienigenaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final AlienigenaRepository alienigenaRepo;

    // Inyección de dependencias mediante constructor
    public DataLoader(AlienigenaRepository alienigenaRepo) {
        this.alienigenaRepo = alienigenaRepo;
    }

    @Override
    public void run(String... args) throws DataException {
        // Verificamos si la base de datos de Postgres ya tiene datos para evitar duplicar registros en cada reinicio
        if (alienigenaRepo.count() == 0) {

            alienigenaRepo.save(crearAlien(
                    "Inferno", "Pyronita", "Pyros", "Piroquinesis",
                    "Ser hecho de magma viviente capaz de proyectar fuego denso y moldear el calor a su antojo.",
                    "https://pbs.twimg.com/media/FumTdM0WwAMFX6r.jpg"
            ));

            alienigenaRepo.save(crearAlien(
                    "Feral", "Vulpimante", "Vulpino", "Sentidos sobrenaturales",
                    "Bestia feroz con habilidades de supervivencia, agilidad y sentidos superdesarrollados.",
                    "https://i.redd.it/k2q1coqbu5oa1.jpg"
            ));

            alienigenaRepo.save(crearAlien(
                    "Cuatro Brazos", "Tetramano", "Khoros", "Fuerza sobrehumana",
                    "Guerrero de musculatura colosal distribuida en cuatro extremidades superiores. Altamente resistente.",
                    "https://cdn.myportfolio.com/87861e13-54ae-4151-bc13-523c9a68a2a3/92965bba-3a88-4099-9f4e-2061ee18bb76_rw_1920.jpg?h=5b85fd72c28f90e48f386d115dd73c63"
            ));

            alienigenaRepo.save(crearAlien(
                    "XLR8", "Kineceleran", "Kinet", "Supervelocidad",
                    "Capaz de manipular la fricción para desplazarse a más de 800 km/h, permitiéndole incluso subir por paredes verticales.",
                    ""
            ));

            alienigenaRepo.save(crearAlien(
                    "Diamantino", "Petrosapien", "Petropia", "Control cristalográfico",
                    "Compuesto por un cristal de silicio orgánico sumamente resistente. Capaz de reflectar luz y regenerarse.",
                    "https://cdna.artstation.com/p/assets/images/images/057/114/252/large/markus-sangalang-ben-10-project-diamondhex-af.jpg?1670865443"
            ));

            alienigenaRepo.save(crearAlien(
                    "Materia Gris", "Galvan", "Galvan Prime", "Superintelecto",
                    "De tamaño diminuto pero poseedor de un cerebro con una capacidad de procesamiento infinitamente superior a la humana.",
                    ""
            ));

            alienigenaRepo.save(crearAlien(
                    "Actualizador", "Mecamorfo Galvánico", "Galvan B", "Fusión tecnológica",
                    "Nanobots líquidos capaces de asimilar cualquier tipo de tecnología no biológica para mejorarla y controlarla.",
                    "https://pbs.twimg.com/media/FO8uM4paAAIhrvm.jpg"
            ));

            alienigenaRepo.save(crearAlien(
                    "Libelulo", "Lepidóptero", "Lepidopterra", "Secreción corrosiva y vuelo",
                    "Un insecto gigante capaz de volar a grandes velocidades y disparar un moco viscoso y en ocasiones corrosivo y tóxico.",
                    "https://cdna.artstation.com/p/assets/images/images/088/799/558/large/brandon-sung-stinkfly-min.jpg?1749200981"
            ));

            alienigenaRepo.save(crearAlien(
                    "Fauces", "Piscciss Volann", "Piscciss", "Adaptación acuática y mandíbula destructora",
                    "Monstruo marino capaz de respirar bajo el agua y utilizar su fuerte mandíbula para destrozar y triturar casi cualquier cosa.",
                    "https://cdna.artstation.com/p/assets/images/images/023/030/314/large/gordon-christian-ripjaws.jpg?1577792172"
            ));

            alienigenaRepo.save(crearAlien(
                    "Espectral", "Ectonurita", "Anur Phaetos", "Intangibilidad y posesión",
                    "Es un fantasma de apariencia aterradora y siniestra capaz de traspasar objetos sólidos y llegar a poseer seres vivos.",
                    "https://preview.redd.it/ghostfreak-fanart-v0-78nob959hvdc1.jpeg?auto=webp&s=98c3d6cb7cacbbee4151d89a6a35e1d54130c8a9"
            ));

            alienigenaRepo.save(crearAlien(
                    "Rayo de cañón", "Pelarota arburiano", "Arburia", "Rodamiento de impacto",
                    "Es un ser de gran tamaño con la capacidad de enrollarse sobre sí mismo, usando esta forma coraza para desplazarse y atacar.",
                    "https://cdnb.artstation.com/p/assets/images/images/066/348/831/large/suprateek-bala-cannonbolt-final.jpg?1692698587"
            ));

            alienigenaRepo.save(crearAlien(
                    "Malahierva", "Florauna", "Flors Verdance", "Clonación vegetal y lianas",
                    "Es una planta gigante capaz de hacer crecer sus extremidades y producir semillas explosivas o de humo desde su espalda.",
                    "https://i.pinimg.com/736x/d8/75/1e/d8751e6db0d8bbf890828bb35e103023.jpg"
            ));

            System.out.println(">>> [CODON STREAM] Base de datos de PostgreSQL inicializada con los 12 alienígenas clásicos.");
        }
    }

    private Alienigena crearAlien(String nombre, String raza, String planeta, String habilidad, String descripcion, String imagenUrl) {
        Alienigena a = new Alienigena();
        a.setNombre(nombre);
        a.setRaza(raza);
        a.setPlaneta(planeta);
        a.setHabilidad(habilidad);
        a.setDescripcion(descripcion);
        a.setImagenUrl(imagenUrl);
        return a;
    }
}