import entidades.*;
import repositorios.InMemoryRepository;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        InMemoryRepository<Empresa> empresaRepository = new InMemoryRepository<>();

        //Crear un país (Argentina).
        Pais argentina = Pais.builder()
                .nombre("Argentina")
                .build();

        //Crear una provincia relacionarla con el país (Buenos Aires)
        Provincia buenosAires = Provincia.builder()
                .id(1L)
                .nombre("Buenos Aires")
                .pais(argentina)
                .build();

        //Crear 1 localidad de Buenos Aires, estableciendo su relación (Caba).
        Localidad caba = Localidad.builder()
                .id(1L)
                .nombre("CABA")
                .provincia(buenosAires)
                .build();

        //Crear un domicilio para Caba
        Domicilio domicilio1 = Domicilio.builder()
                .id(1L)
                .calle("9 de Julio")
                .numero(1100)
                .cp(5657)
                .localidad(caba)
                .build();

        //Crear otra localidad de Buenos Aires, estableciendo su relación (La Plata).
        Localidad laPlata = Localidad.builder()
                .id(2L)
                .nombre("La Plata")
                .provincia(buenosAires)
                .build();

        //Crear un domicilio para La Plata
        Domicilio domicilio2 = Domicilio.builder()
                .id(2L)
                .calle("Benavidez")
                .numero(1200)
                .cp(5985)
                .localidad(laPlata)
                .build();

        //Crear otra provincia relacionarla con el país (Córdoba).
        Provincia cordoba = Provincia.builder()
                .id(2L)
                .nombre("Córdoba")
                .pais(argentina)
                .build();

        //Crear 1 localidad de Córdoba, estableciendo su relación (Córdoba Capital).
        Localidad cordobaCapital = Localidad.builder()
                .id(3L)
                .nombre("Córdoba Capital")
                .provincia(cordoba)
                .build();

        //Crear un domicilio para Córdoba Capital.
        Domicilio domicilio3 = Domicilio.builder()
                .id(3L).calle("Rodrigo Bueno")
                .numero(2011)
                .cp(2406)
                .localidad(cordobaCapital)
                .build();

        //Crear otra localidad de Córdoba, estableciendo su relación (Villa Carlos Paz).
        Localidad villaCarlosPaz = Localidad.builder()
                .id(4L)
                .nombre("Villa Carlos Paz")
                .provincia(cordoba)
                .build();

        //Crear un domicilio para Villa Carlos Paz.
        Domicilio domicilio4 = Domicilio.builder()
                .id(4L)
                .calle("Aramendi")
                .numero(2017)
                .cp(2910)
                .localidad(villaCarlosPaz)
                .build();

        //Crear la Sucursal1 y relacionarla con el domicilio de Caba.
        Sucursal sucursal1 = Sucursal.builder()
                .id(1L)
                .nombre("Sucursal 1")
                .horarioApertura(LocalTime.of(9, 0))
                .horarioCierre(LocalTime.of(18, 0))
                .esCasaMatriz(true)
                .domicilio(domicilio1)
                .build();

        //Crear la Sucursal2 y relacionarla con el domicilio de La Plata.
        Sucursal sucursal2 = Sucursal.builder()
                .id(2L)
                .nombre("Sucursal 2")
                .horarioApertura(LocalTime.of( 8, 0))
                .horarioCierre(LocalTime.of( 17,  0))
                .esCasaMatriz(false)
                .domicilio(domicilio2)
                .build();

        //Crear la Sucursal3 y relacionarla con el domicilio de Córdoba Capital.
        Sucursal sucursal3 = Sucursal.builder()
                .id(3L)
                .nombre("Sucursal 3")
                .horarioApertura(LocalTime.of(8, 30))
                .horarioCierre(LocalTime.of(19, 30))
                .esCasaMatriz(true)
                .domicilio(domicilio3)
                .build();

        //Crear la Sucursal2 y relacionarla con el domicilio de Villa Carlos Paz.
        Sucursal sucursal4 = Sucursal.builder()
                .id(4L)
                .nombre("Sucursal 4")
                .horarioApertura(LocalTime.of(9, 0))
                .horarioCierre(LocalTime.of(19, 0))
                .esCasaMatriz(false)
                .domicilio(domicilio4)
                .build();

        //Crear la Empresa1 y relacionarlas con la sucursal 1 y 2.
        Empresa empresa1 = Empresa.builder()
                .nombre("Empresa 1")
                .razonSocial("Razón Social 1")
                .cuil(20464753169L)
                .sucursales(Set.of(sucursal1, sucursal2))
                .build();

        //Crear la Empresa2 y relacionarla con la sucursal 3 y 4.
        Empresa empresa2 = Empresa.builder()
                .nombre("Empresa 2")
                .razonSocial("Razón Social 2")
                .cuil(123456789L)
                .sucursales(Set.of(sucursal3, sucursal4))
                .build();

        //Guardar empresas en el repositorio
        empresaRepository.save(empresa1);
        empresaRepository.save(empresa2);
        System.out.println("--------------------------------------------------");

        //Mostrar todas las empresas.
        System.out.println("Mostrando todas las empresas: ");
        List<Empresa> todasLasEmpresas = empresaRepository.findAll();
        todasLasEmpresas.forEach(System.out::println);
        System.out.println("--------------------------------------------------");

        //Buscar una empresa por su Id.
        Optional<Empresa> empresaEncontrada = empresaRepository.findById(1L);
        empresaEncontrada.ifPresent(e -> System.out.println("Empresa encontrada por ID 1: " + e));
        System.out.println("--------------------------------------------------");

        //Buscar una empresa por nombre.
        List<Empresa> empresasPorNombre = empresaRepository.genericFindByField("nombre", "Empresa 1");
        System.out.println("Empresas con nombre 'Empresa 1':");
        empresasPorNombre.forEach(System.out::println);
        System.out.println("--------------------------------------------------");

        //Actualizar los datos de una empresa buscando por su ID. Por ejemplo modificar su cuil.
        Empresa empresaActualizada = Empresa.builder()
                .id(1L)
                .nombre("Empresa 1 Actualizada")
                .razonSocial("Razon Social 1 Actualizada")
                .cuil(12345678901L)
                .sucursales(empresa1.getSucursales())
                .build();

        empresaRepository.genericUpdate(1L, empresaActualizada);
        Optional<Empresa> empresaVerificada = empresaRepository.findById(1L);
        empresaVerificada.ifPresent(e -> System.out.println("Empresa después de la actualización: " + e));
        System.out.println("--------------------------------------------------");

        //Eliminar una empresa buscando por su ID.
        empresaRepository.genericDelete(1L);
        Optional<Empresa> empresaEliminada = empresaRepository.findById(1L);
        if (empresaEliminada.isEmpty()) {
            System.out.println("La empresa con ID 1 ha sido eliminada.");
        }
    }
}