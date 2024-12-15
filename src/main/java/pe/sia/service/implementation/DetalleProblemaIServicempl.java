package pe.sia.service.implementation;

import java.time.Instant;
import java.util.*;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import pe.sia.persistence.entity.problema.DetalleProblema;
import pe.sia.persistence.repository.problemaRepository.CategoriaRepository;
import pe.sia.persistence.repository.problemaRepository.PrioridadRepository;
import pe.sia.persistence.repository.problemaRepository.DetalleProblemaRepository;
import pe.sia.persistence.repository.problemaRepository.ProblemaGeneralRepository;
import pe.sia.presentation.dto.problemaDTO.DetalleProblemaDTO;
import pe.sia.presentation.dto.problemaDTO.PrioridadDTO;
import pe.sia.service.interfaces.DetalleProblemaService;
import pe.sia.util.UtilsApp;

@Service
@Slf4j
public class DetalleProblemaIServicempl implements DetalleProblemaService {

    private final DetalleProblemaRepository detalleProblemaRepository;
    private final CategoriaRepository categoriaRepository;
    private final PrioridadRepository prioridadRepository;
    private final ProblemaGeneralRepository problemaGeneralRepository;

    public DetalleProblemaIServicempl(DetalleProblemaRepository detalleProblemaRepository, CategoriaRepository categoriaRepository,
                                      PrioridadRepository prioridadRepository, ProblemaGeneralRepository problemaGeneralRepository) {
        this.detalleProblemaRepository = detalleProblemaRepository;
        this.categoriaRepository = categoriaRepository;
        this.prioridadRepository = prioridadRepository;
        this.problemaGeneralRepository = problemaGeneralRepository;
    }

    @Override
    public DetalleProblemaDTO findAllDetalleProblema(Integer idProblemaGeneral) {
        DetalleProblemaDTO requestDTO = new DetalleProblemaDTO();
        try {
            List<Object[]> listResults = detalleProblemaRepository.findAllDetalleProblema(idProblemaGeneral);
            List<DetalleProblemaDTO> listaDetalleProblemaDTO = new ArrayList<>();

            if (!listResults.isEmpty()) {
                for (Object[] object : listResults) {
                    DetalleProblemaDTO detalleProblemaDTO = new DetalleProblemaDTO();
                    detalleProblemaDTO.setStatusCode(200);
                    detalleProblemaDTO.setIdDetalleProblema((Integer) object[0]);
                    detalleProblemaDTO.setCodigoProblema(object[1].toString());
                    detalleProblemaDTO.setCodigoProblemaGeneral(object[2].toString());
                    detalleProblemaDTO.setDescripcion(object[3].toString());
                    detalleProblemaDTO.setFechaRegistro(UtilsApp.formatearFecha((Instant) object[4]));
                    detalleProblemaDTO.setMedioReporte(object[5].toString());
                    detalleProblemaDTO.setSolucion(object[6].toString());
                    detalleProblemaDTO.setCodigoBien(object[7].toString());
                    detalleProblemaDTO.setNombreActivo(object[8].toString());
                    detalleProblemaDTO.setTipoActivo(object[9].toString());
                    detalleProblemaDTO.setNombreEmpleado(object[10].toString());
                    detalleProblemaDTO.setPrioridad(object[11].toString());
                    detalleProblemaDTO.setCategoria(object[12].toString());
                    detalleProblemaDTO.setSolucionado((Boolean) object[13]);
                    listaDetalleProblemaDTO.add(detalleProblemaDTO);
                }
                requestDTO.setStatusCode(200);
                requestDTO.setMessage(idProblemaGeneral != 0 ? "Listado de detalle problema por ID" : "Listado de problema con éxito");
                requestDTO.setDetalleProblemaDTOList(listaDetalleProblemaDTO);
            } else {
                requestDTO.setStatusCode(404);
                requestDTO.setMessage("Lista vacia, no hay datos");
            }
            return requestDTO;

        } catch (Exception e) {
            requestDTO.setStatusCode(500);
            requestDTO.setError("Ha ocurrido un error inexperado al listar las problema: " + e.getMessage());
            return requestDTO;
        }
    }

    @Override
    public DetalleProblemaDTO insertarDetalleProblema(DetalleProblemaDTO detalleProblemaDTO) {
        DetalleProblemaDTO requestDTO = new DetalleProblemaDTO();
        try {
            DetalleProblema detalleProblema = new DetalleProblema();

            detalleProblema.setDescripcion(detalleProblemaDTO.getDescripcion());
            detalleProblema.setFechaRegistro(UtilsApp.formatearFechaInstant(detalleProblemaDTO.getFechaRegistro()));
            detalleProblema.setMedioReporte(detalleProblemaDTO.getMedioReporte());
            detalleProblema.setSolucion(detalleProblemaDTO.getSolucion());
            detalleProblema.setCategoria(categoriaRepository.findById(detalleProblemaDTO.getCategoriaId()).orElse(null));
            detalleProblema.setPrioridad(prioridadRepository.findById(detalleProblemaDTO.getPrioridadId()).orElse(null));
            detalleProblema.setSolucionado(false);
            detalleProblema.setProblemaGeneral(problemaGeneralRepository.findById(detalleProblemaDTO.getIdProblemaGeneral()).orElse(null));
            DetalleProblema newDetalleProblema = detalleProblemaRepository.save(detalleProblema);
            if(newDetalleProblema.getId() > 0) {
                requestDTO.setDetalleProblema(detalleProblema);
                requestDTO.setStatusCode(201);
                requestDTO.setMessage("Se ha creado el detalle del problema correctamente");
            }
            return requestDTO;
        } catch (Exception e) {
            requestDTO.setStatusCode(500);
            requestDTO.setMessage("Error al crear el detalle problema: " + e.getMessage());
            return requestDTO;
        }
    }

    @Override
    public DetalleProblemaDTO actualizarDetalleProblema(Integer id, DetalleProblemaDTO detalleProblemaUpdate) {
        DetalleProblemaDTO requestDTO = new DetalleProblemaDTO();
        try {
            Optional<DetalleProblema> detalleProblemaOptional = detalleProblemaRepository.findById(id);
            detalleProblemaOptional.ifPresentOrElse( detalleProblema -> {
                detalleProblema.setDescripcion(detalleProblemaUpdate.getDescripcion());
                detalleProblema.setFechaRegistro(UtilsApp.formatearFechaInstant(detalleProblemaUpdate.getFechaRegistro()));
                detalleProblema.setMedioReporte(detalleProblemaUpdate.getMedioReporte());
                detalleProblema.setSolucion(detalleProblemaUpdate.getSolucion());
                detalleProblema.setCategoria(categoriaRepository.findById(detalleProblemaUpdate.getCategoriaId()).orElse(null));
                detalleProblema.setPrioridad(prioridadRepository.findById(detalleProblemaUpdate.getPrioridadId()).orElse(null));
                detalleProblema.setSolucionado(detalleProblemaUpdate.getSolucionado());
                detalleProblema.setProblemaGeneral(problemaGeneralRepository.findById(detalleProblemaUpdate.getIdProblemaGeneral()).orElse(null));
                DetalleProblema updateDetalleProblema = detalleProblemaRepository.save(detalleProblema);
                requestDTO.setStatusCode(201);
                requestDTO.setMessage("El detalle problema se ha actualizado con éxito");
                requestDTO.setDetalleProblema(updateDetalleProblema);
            }, () -> {
                requestDTO.setStatusCode(404);
                requestDTO.setMessage("El detalle problema no se encontrado");
            });
        } catch (Exception e) {
            requestDTO.setStatusCode(500);
            requestDTO.setError("Ha ocurrido un error inesperado al actualizar el detalle problema: " + e.getMessage());
        }
        return requestDTO;
    }

    @Override
    public DetalleProblemaDTO eliminarDetalleProblema(Integer id) {
        DetalleProblemaDTO requestDTO = new DetalleProblemaDTO();
        try {
            Optional<DetalleProblema> itemOptional = detalleProblemaRepository.findById(id);
            if (itemOptional.isPresent()) {
                detalleProblemaRepository.deleteById(id);
                requestDTO.setStatusCode(204);
                requestDTO.setMessage("El detalle problema se ha eliminado con éxito");
            } else {
                requestDTO.setStatusCode(404);
                requestDTO.setMessage("Detalle Problema no encontrada");
            }

        } catch (Exception e) {
            requestDTO.setStatusCode(500);
            requestDTO.setError("Ha ocurrido un error inesperado al eliminar el detalle problema: " + e.getMessage());
        }
        return requestDTO;
    }

    @Override
    public DetalleProblemaDTO buscarDetalleProblema(String codigoProblema) {
        DetalleProblemaDTO requestDTO = new DetalleProblemaDTO();
        try {
            Optional<DetalleProblema> itemOptional = detalleProblemaRepository.findByCodigoProblema(codigoProblema);
            if (itemOptional.isPresent()) {
                DetalleProblema detalleProblema = itemOptional.get();
                requestDTO.setCodigoProblema(detalleProblema.getCodigoProblema());
                requestDTO.setDescripcion(detalleProblema.getDescripcion());
                requestDTO.setFechaRegistro(detalleProblema.getFechaRegistro().toString());
                requestDTO.setMedioReporte(detalleProblema.getMedioReporte());
                requestDTO.setCategoria(detalleProblema.getCategoria().getNombre());
                requestDTO.setPrioridad(detalleProblema.getPrioridad().getNombre());
                requestDTO.setSolucionado(detalleProblema.isSolucionado());
                requestDTO.setCodigoProblemaGeneral(detalleProblema.getProblemaGeneral().getCodigoProblemaGeneral());
                requestDTO.setStatusCode(200);
                requestDTO.setMessage("Detalle problema encontrado con exito");
            } else {
                requestDTO.setStatusCode(404);
                requestDTO.setMessage("Detalle problema no encontrado");
            }

        } catch (Exception e) {
            requestDTO.setStatusCode(500);
            requestDTO.setError("Ha ocurrido un error al buscar el detalle problema: " + e.getMessage());
        }
        return requestDTO;
    }

    @Override
    public Integer getDetalleProblemaSolucionados() {
        int totalIncidenciasSolucionadas;
        totalIncidenciasSolucionadas = detalleProblemaRepository.getDetalleProblemaSolucionados();
        return totalIncidenciasSolucionadas;
    }

    @Override
    public Integer getPromedioDetalleProblemaResueltos() {
        int promedioIncidenciasSolucionadas;
        promedioIncidenciasSolucionadas = detalleProblemaRepository.getPromedioDetalleProblemaResueltos();
        return promedioIncidenciasSolucionadas;
    }

    @Override
    public Map<String, Object> getMaxEmpleadoDetalleProblema() {
        List<Map<String, Object>> resultadoTabla = detalleProblemaRepository.getMaxEmpleadoDetalleProblema();

        Map<String, Object> empleado = resultadoTabla.getFirst();

        return Map.of(
                "nombre", empleado.get("nombre"),
                "cantidad", empleado.get("cantidad")
        );
    }

    @Override
    public Integer getPromedioMaxEmpleadoDetalleProblema() {
        int promedio;
        promedio = detalleProblemaRepository.getPromedioMaxEmpleadoDetalleProblema();
        return promedio;
    }

    @Override
    public Integer getTotalDetalleProblemaAyer() {
        int total;
        total = detalleProblemaRepository.getTotalDetalleProblemaAyer();
        return total;
    }

    @Override
    public Integer getPromedioDetalleProbemaEntreAyerHoy() {
        int promedio;
        promedio = detalleProblemaRepository.getPromedioDetalleProbemaEntreAyerHoy();
        return promedio;
    }

    @Override
    public DetalleProblemaDTO getCantidadTotalDetallePromedioPorMes() {
        DetalleProblemaDTO requestDTO = new DetalleProblemaDTO();
        try {

            List<Object[]> resultTable = detalleProblemaRepository.getCantidadTotalDetallePromedioPorMes();
            List<DetalleProblemaDTO> listMesIncidenia = new ArrayList<>();

            if (!resultTable.isEmpty()) {
                for (Object[] object : resultTable) {
                    DetalleProblemaDTO incidenciaMes = new DetalleProblemaDTO.Builder()
                            .statusCode(200)
                            .mes((Integer) object[0])
                            .cantidadMes((Integer) object[1])
                            .build();
                    listMesIncidenia.add(incidenciaMes);
                }
                requestDTO.setStatusCode(200);
                requestDTO.setMessage("Se ha extraido la cantidad de problema por mes con éxito");
                requestDTO.setDetalleProblemaDTOList(listMesIncidenia);
            }
            return requestDTO;

        } catch (Exception e) {
            requestDTO.setStatusCode(500);
            requestDTO.setError("Ha ocurrido un error inexperado al obtener la cantidad de cada mes: " + e.getMessage());
            return requestDTO;
        }
    }

    @Override
    public DetalleProblemaDTO getMedidasDetalleProblemaMantenimiento() {
        DetalleProblemaDTO requestDTO = new DetalleProblemaDTO();
        try {
            List<Object[]> resultTable = detalleProblemaRepository.getMedidasDetalleProblemaMantenimiento();
            if (!resultTable.isEmpty()) {
                Object[] fila = resultTable.getFirst();
                DetalleProblemaDTO detalleProblemaDTO = new DetalleProblemaDTO.Builder()
                        .statusCode(200)
                        .cantidadTotalFallo(((Number) fila[0]).intValue())
                        .porcentajeSolucionado(((Number) fila[1]).intValue())
                        .cantidadSolucionado(((Number) fila[2]).intValue())
                        .porcentajeMantenimiento(((Number) fila[3]).intValue())
                        .cantidadMantenimiento(((Number) fila[4]).intValue())
                        .build();
                requestDTO.setStatusCode(200);
                requestDTO.setMessage("Se ha extraido la cantidad, porcentaje, cantidad de problema con éxito");
                requestDTO.setDetalleProblemaDTO(detalleProblemaDTO);
            } else {
                requestDTO.setStatusCode(404);
                requestDTO.setMessage("No se ha encontrado datos");
            }
            return requestDTO;
        } catch (Exception e) {
            requestDTO.setStatusCode(500);
            requestDTO.setError("Ha ocurrido un error inexperado al obtener la cantidad, porcentaje, cantidad de problema: " + e.getMessage());
            return requestDTO;
        }
    }

    @Override
    public DetalleProblemaDTO getCantidadDetalleProblemaPorDiaMesActualAnterior() {
        DetalleProblemaDTO requestDTO = new DetalleProblemaDTO();
        try{
            List<Object[]> resultTable = detalleProblemaRepository.getCantidadDetalleProblemaPorDiaMesActualAnterior();
            List<DetalleProblemaDTO> listMesActualAnterior = new ArrayList<>();
            if(!resultTable.isEmpty()) {
                for(Object[] fila : resultTable) {
                    DetalleProblemaDTO incidencia = new DetalleProblemaDTO.Builder()
                            .statusCode(200)
                            .diaSemana((String) fila[0])
                            .cantidadMesAnterior((Integer) fila[1])
                            .cantidadMesActual((Integer) fila[2])
                            .build();
                    listMesActualAnterior.add(incidencia);
                }
                requestDTO.setStatusCode(200);
                requestDTO.setMessage("Obteniendo la cantidad de los dias el mes anterior y actual");
                requestDTO.setDetalleProblemaDTOList(listMesActualAnterior);
            }
            else {
                requestDTO.setStatusCode(404);
                requestDTO.setMessage("No se ha encontrado datos");
            }
            return requestDTO;
        } catch (Exception e) {
            requestDTO.setStatusCode(500);
            requestDTO.setError("Ha ocurrido un error inesperado al obtener la data: " + e.getMessage());
            return requestDTO;
        }
    }

    @Override
    public DetalleProblemaDTO getDetalleProblemaTotalMesActualAnterior() {
        DetalleProblemaDTO requestDTO = new DetalleProblemaDTO();
        try {
            List<Object[]> resultTable = detalleProblemaRepository.getDetalleProblemaTotalMesActualAnterior();
            if (!resultTable.isEmpty()) {
                Object[] fila = resultTable.getFirst();
                DetalleProblemaDTO detalleProblemaDTO = new DetalleProblemaDTO.Builder()
                        .statusCode(200)
                        .cantidadMesAnterior((Integer) fila[0])
                        .cantidadMesActual((Integer) fila[1])
                        .build();
                requestDTO.setStatusCode(200);
                requestDTO.setMessage("Se ha extraido la cantidad de problema del mes anterior y actual");
                requestDTO.setDetalleProblemaDTO(detalleProblemaDTO);
            } else {
                requestDTO.setStatusCode(404);
                requestDTO.setMessage("No se ha encontrado datos");
            }
            return requestDTO;
        } catch (Exception e) {
            requestDTO.setStatusCode(500);
            requestDTO.setError("Ha ocurrido un error inexperado al obtener la cantidad de problema del mes anterior y actual: " + e.getMessage());
            return requestDTO;
        }
    }

    @Override
    public DetalleProblemaDTO getTotalDetalleProblemaByPrioridadByCategoria() {
        DetalleProblemaDTO requestDTO = new DetalleProblemaDTO();
        try {
            List<Object[]> resultTable = detalleProblemaRepository.getTotalDetalleProblemaByPrioridadByCategoria();
            List<PrioridadDTO> prioridadDTOList = new ArrayList<>();
            if (!resultTable.isEmpty()) {
                for(Object[] fila : resultTable) {
                    PrioridadDTO incidenciaFalloDTO = new PrioridadDTO.Builder()
                            .statusCode(200)
                            .nombre((String) fila[0])
                            .cantidadLeve((Integer) fila[1])
                            .cantidadGrave((Integer) fila[2])
                            .cantidadCritico((Integer) fila[3])
                            .mes((String) fila[4])
                            .build();
                    prioridadDTOList.add(incidenciaFalloDTO);
                }
                requestDTO.setStatusCode(200);
                requestDTO.setMessage("Se ha extraido la cantidad de problema/fallos del mes anterior y actual por prioridad");
                requestDTO.setListPrioridadDTO(prioridadDTOList);
            } else {
                requestDTO.setStatusCode(404);
                requestDTO.setMessage("No se ha encontrado datos");
            }
            return requestDTO;
        } catch (Exception e) {
            requestDTO.setStatusCode(500);
            requestDTO.setError("Ha ocurrido un error inexperado al obtener la cantidad de problema/fallos del mes anterior y actual por prioridad: " + e.getMessage());
            return requestDTO;
        }
    }

    @Override
    public DetalleProblemaDTO getDetalleProblemaPorIdProblemaGeneral(Integer idProblemaGeneral) {
        DetalleProblemaDTO requestDTO = new DetalleProblemaDTO();
        try {
            List<Object[]> listResults = detalleProblemaRepository.getDetalleProblemaPorIdPG(idProblemaGeneral);
            List<DetalleProblemaDTO> listaDetalleProblemaDTO = new ArrayList<>();
            if (!listResults.isEmpty()) {
                for (Object[] object : listResults) {
                    DetalleProblemaDTO detalleProblemaDTO = new DetalleProblemaDTO();
                    detalleProblemaDTO.setStatusCode(200);
                    detalleProblemaDTO.setIdDetalleProblema((Integer) object[0]);
                    detalleProblemaDTO.setCodigoProblema(object[1].toString());
                    detalleProblemaDTO.setCodigoProblemaGeneral(object[2].toString());
                    detalleProblemaDTO.setDescripcion(object[3].toString());
                    detalleProblemaDTO.setFechaRegistro(UtilsApp.formatearFecha((Instant) object[4]));
                    detalleProblemaDTO.setMedioReporte(object[5].toString());
                    detalleProblemaDTO.setSolucion(object[6].toString());
                    detalleProblemaDTO.setCodigoBien(object[7].toString());
                    detalleProblemaDTO.setNombreActivo(object[8].toString());
                    detalleProblemaDTO.setTipoActivo(object[9].toString());
                    detalleProblemaDTO.setNombreEmpleado(object[10].toString());
                    detalleProblemaDTO.setPrioridad(object[11].toString());
                    detalleProblemaDTO.setCategoria(object[12].toString());
                    detalleProblemaDTO.setSolucionado((Boolean) object[13]);
                    listaDetalleProblemaDTO.add(detalleProblemaDTO);
                }
                requestDTO.setStatusCode(200);
                requestDTO.setMessage("Listado de problema con éxito");
                requestDTO.setDetalleProblemaDTOList(listaDetalleProblemaDTO);
            } else {
                requestDTO.setStatusCode(404);
                requestDTO.setMessage("Lista vacia, no hay datos");
            }
            return requestDTO;
        } catch (Exception e) {
            requestDTO.setStatusCode(500);
            requestDTO.setError("Ha ocurrido un error inexperado al listar las problema: " + e.getMessage());
            return requestDTO;
        }
    }

}
