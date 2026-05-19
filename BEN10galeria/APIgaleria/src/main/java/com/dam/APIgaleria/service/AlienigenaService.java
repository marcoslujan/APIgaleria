package com.dam.APIgaleria.service;
import com.dam.APIgaleria.model.Alienigena;
import com.dam.APIgaleria.repository.AlienigenaRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlienigenaService {
    private final AlienigenaRepository alienigenaRepo;

    public AlienigenaService(AlienigenaRepository alienigenaRepo){this.alienigenaRepo = alienigenaRepo; }

    public List<Alienigena> listar() {return alienigenaRepo.findAll();}

    public Alienigena guardar(Alienigena alienigena) {return alienigenaRepo.save(alienigena);}

    public Optional<Alienigena> buscarPorId(Long id) {return alienigenaRepo.findById(id);}
    public void eliminar(Long id) {alienigenaRepo.deleteById(id);}

    @PostConstruct
    public void inicializarBaseDatos() {
        if (alienigenaRepo.count() == 0) {

            alienigenaRepo.save(crearAlien(
                    "Fuego", "Pyronita", "Pyros", "Piroquinesis",
                    "Ser hecho de magma viviente capaz de proyectar fuego denso y moldear el calor a su antojo.",
                    "https://images.unsplash.com/photo-1578301978693-85fa9c0320b9?auto=format&fit=crop&q=80&w=600"
            ));

            alienigenaRepo.save(crearAlien(
                    "Cuatro Brazos", "Tetramand", "Khoros", "Fuerza sobrehumana",
                    "Guerrero de musculatura colosal distribuida en cuatro extremidades superiores. Altamente resistente.",
                    "https://images.unsplash.com/photo-1589254065878-42c9da997008?auto=format&fit=crop&q=80&w=600"
            ));

            alienigenaRepo.save(crearAlien(
                    "XLR8", "Kineceleran", "Kinet", "Velocidad hipersónica",
                    "Capaz de manipular la fricción para desplazarse a más de 800 km/h, permitiéndole subir por paredes verticales.",
                    "https://images.unsplash.com/photo-1518156677180-95a2893f3e9f?auto=format&fit=crop&q=80&w=600"
            ));

            alienigenaRepo.save(crearAlien(
                    "Diamante", "Petrosapien", "Petropia", "Control cristalográfico",
                    "Compuesto por un cristal de silicio orgánico sumamente resistente. Capaz de reflectar luz y regenerarse.",
                    "https://images.unsplash.com/photo-1515688594390-b649af70d282?auto=format&fit=crop&q=80&w=600"
            ));

            alienigenaRepo.save(crearAlien(
                    "Materia Gris", "Galvan", "Galvan Prime", "Superintelecto",
                    "De tamaño diminuto pero poseedor de un cerebro con una capacidad de procesamiento infinitamente superior a la humana.",
                    "https://images.unsplash.com/photo-1507668077129-56e32842fceb?auto=format&fit=crop&q=80&w=600"
            ));

            alienigenaRepo.save(crearAlien(
                    "Actualización", "Mecamorfo Galvánico", "Galvan B", "Fusión tecnológica",
                    "Nanobots líquidos capaces de asimilar cualquier tipo de tecnología no biológica para mejorarla y controlarla.",
                    "https://images.unsplash.com/photo-1526374965328-7f61d4dc18c5?auto=format&fit=crop&q=80&w=600"
            ));
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
