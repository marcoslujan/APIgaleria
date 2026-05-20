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
                    "https://images.unsplash.com/photo-1589254065878-42c9da997008?auto=format&fit=crop&q=80&w=600"
            ));

            alienigenaRepo.save(crearAlien(
                    "XLR8", "Kineceleran", "Kinet", "Supervelocidad",
                    "Capaz de manipular la fricción para desplazarse a más de 800 km/h, permitiéndole incluso subir por paredes verticales.",
                    "https://images.unsplash.com/photo-1518156677180-95a2893f3e9f?auto=format&fit=crop&q=80&w=600"
            ));

            alienigenaRepo.save(crearAlien(
                    "Diamantino", "Petrosapien", "Petropia", "Control cristalográfico",
                    "Compuesto por un cristal de silicio orgánico sumamente resistente. Capaz de reflectar luz y regenerarse.",
                    "https://images.unsplash.com/photo-1515688594390-b649af70d282?auto=format&fit=crop&q=80&w=600"
            ));

            alienigenaRepo.save(crearAlien(
                    "Materia Gris", "Galvan", "Galvan Prime", "Superintelecto",
                    "De tamaño diminuto pero poseedor de un cerebro con una capacidad de procesamiento infinitamente superior a la humana.",
                    "https://images.unsplash.com/photo-1507668077129-56e32842fceb?auto=format&fit=crop&q=80&w=600"
            ));

            alienigenaRepo.save(crearAlien(
                    "Actualizador", "Mecamorfo Galvánico", "Galvan B", "Fusión tecnológica",
                    "Nanobots líquidos capaces de asimilar cualquier tipo de tecnología no biológica para mejorarla y controlarla.",
                    "https://images.unsplash.com/photo-1526374965328-7f61d4dc18c5?auto=format&fit=crop&q=80&w=600"
            ));

            alienigenaRepo.save(crearAlien(
                    "Libelulo", "Lepidóptero", "Lepidopterra", "Secreción corrosiva y vuelo",
                    "Un insecto gigante capaz de volar a grandes velocidades y disparar un moco viscoso y en ocasiones corrosivo y tóxico.",
                    "https://images.unsplash.com/photo-1541185933-ef5d8ed016c2?auto=format&fit=crop&q=80&w=600"
            ));

            alienigenaRepo.save(crearAlien(
                    "Fauces", "Piscciss Volann", "Piscciss", "Adaptación acuática y mandíbula destructora",
                    "Monstruo marino capaz de respirar bajo el agua y utilizar su fuerte mandíbula para destrozar y triturar casi cualquier cosa.",
                    "https://images.unsplash.com/photo-1522069169874-c58ec4b76be5?auto=format&fit=crop&q=80&w=600"
            ));

            alienigenaRepo.save(crearAlien(
                    "Espectral", "Ectonurita", "Anur Phaetos", "Intangibilidad y posesión",
                    "Es un fantasma de apariencia aterradora y siniestra capaz de traspasar objetos sólidos y llegar a poseer seres vivos.",
                    "https://images.unsplash.com/photo-1509248961158-e54f6934749c?auto=format&fit=crop&q=80&w=600"
            ));

            alienigenaRepo.save(crearAlien(
                    "Rayo de cañón", "Pelarota arburiano", "Arburia", "Rodamiento de impacto",
                    "Es un ser de gran tamaño con la capacidad de enrollarse sobre sí mismo, usando esta forma coraza para desplazarse y atacar.",
                    "https://images.unsplash.com/photo-1534361960057-19889db9621e?auto=format&fit=crop&q=80&w=600"
            ));

            alienigenaRepo.save(crearAlien(
                    "Malahierva", "Florauna", "Flors Verdance", "Clonación vegetal y lianas",
                    "Es una planta gigante capaz de hacer crecer sus extremidades y producir semillas explosivas o de humo desde su espalda.",
                    "https://images.unsplash.com/photo-1470071459604-3b5ec3a7fe05?auto=format&fit=crop&q=80&w=600"
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