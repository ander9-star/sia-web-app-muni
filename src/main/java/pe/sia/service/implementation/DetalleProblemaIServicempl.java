package pe.sia.service.implementation;

import java.time.Instant;
import java.util.*;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import pe.sia.persistence.entity.activos.ActivoMaestro;
import pe.sia.persistence.entity.actores.Usuario;
import pe.sia.persistence.entity.problema.Categoria;
import pe.sia.persistence.entity.problema.DetalleProblema;
import pe.sia.persistence.entity.problema.Prioridad;
import pe.sia.persistence.repository.activosRepository.ActivoMaestroRepository;
import pe.sia.persistence.repository.actoresRepository.UsuarioRepository;
import pe.sia.persistence.repository.problemaRepository.CategoriaRepository;
import pe.sia.persistence.repository.problemaRepository.PrioridadRepository;
import pe.sia.persistence.repository.problemaRepository.DetalleProblemaRepository;
import pe.sia.presentation.dto.problemaDTO.DetalleProblemaDTO;
import pe.sia.presentation.dto.problemaDTO.PrioridadDTO;
import pe.sia.service.interfaces.ProblemaDetalleService;
import pe.sia.util.UtilsApp;

@Service
@Slf4j
public class ProblemaDetalleIServicempl implements ProblemaDetalleService {

    private final DetalleProblemaRepository detalleProblemaRepository;
    private final ActivoMaestroRepository activoMaestroRepository;
    private final CategoriaRepository categoriaRepository;
    private final PrioridadRepository prioridadRepository;
    private final UsuarioRepository usuarioRepository;

    public ProblemaDetalleIServicempl(DetalleProblemaRepository detalleProblemaRepository, ActivoMaestroRepository activoMaestroRepository,
                                      CategoriaRepository categoriaRepository, PrioridadRepository prioridadRepository, UsuarioRepository usuarioRepository) {
        this.detalleProblemaRepository = detalleProblemaRepository;
        this.activoMaestroRepository = activoMaestroRepository;
        this.categoriaRepository = categoriaRepository;
        this.prioridadRepository = prioridadRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public DetalleProblemaDTO getIncidenciaFallo() {
        DetalleProblemaDTO requestDTO = new DetalleProblemaDTO();
        try {
            List<Object[]> listResults = detalleProblemaRepository.findAllDetalleProblema();
            List<DetalleProblemaDTO> listFalloDTO = new ArrayList<>();

            if (!listResults.isEmpty()) {
                for (Object[] object : listResults) {
                    DetalleProblemaDTO falloIncidenciaDTO = new DetalleProblemaDTO();
                    falloIncidenciaDTO.setStatusCode(200);
                    falloIncidenciaDTO.setIdDetalleProblema((Integer) object[0]);
                    falloIncidenciaDTO.setCodigoProblema((String) object[1]);
                    falloIncidenciaDTO.setCodigoProblemaGeneral((String) object[2]);
                    falloIncidenciaDTO.setDescripcion((String) object[2]);
                    falloIncidenciaDTO.setFechaRegistro(UtilsApp.formatearFecha((Instant) object[3]));
                    falloIncidenciaDTO.setMedioReporte((String) object[4]);
                    falloIncidenciaDTO.setSolucion((String) object[5]);
                    falloIncidenciaDTO.setCodigoBien((String) object[6]);
                    falloIncidenciaDTO.setNombreActivo((String) object[7]);
                    falloIncidenciaDTO.setTipoActivo((String) object[8]);
                    falloIncidenciaDTO.setNombreEmpleado((String) object[9]);
                    falloIncidenciaDTO.setPrioridad((String) object[10]);
                    falloIncidenciaDTO.setCategoria((String) object[11]);
                    falloIncidenciaDTO.setSolucionado((Boolean) object[12]);
                    falloIncidenciaDTO.setNombreUsuario((String) object[13]);
                    listFalloDTO.add(falloIncidenciaDTO);
                }

                requestDTO.setStatusCode(200);
                requestDTO.setMessage("Listado de problema con éxito");
                requestDTO.setListFalloDetalleProblemaDTO(listFalloDTO);
            }
            return requestDTO;

        } catch (Exception e) {
            requestDTO.setStatusCode(500);
            requestDTO.setError("Ha ocurrido un error inexperado al listar las problema: " + e.getMessage());
            return requestDTO;
        }
    }

    @Override
    public DetalleProblemaDTO insertarIncidencia(DetalleProblemaDTO detalleProblemaDTO) {
        DetalleProblemaDTO requestDTO = new DetalleProblemaDTO();
        try {
            DetalleProblema falloIncidencia = new DetalleProblema();

            falloIncidencia.setDescripcion(detalleProblemaDTO.getDescripcion());
            falloIncidencia.setFechaRegistro(UtilsApp.formatearFechaInstant(detalleProblemaDTO.getFechaRegistro()));
            falloIncidencia.setMedioReporte(detalleProblemaDTO.getMedioReporte());
            falloIncidencia.setSolucion(detalleProblemaDTO.getSolucion());

            ActivoMaestro activoMaestro = activoMaestroRepository.findById(detalleProblemaDTO.getActivoMaestroId()).orElse(null);
            Categoria categoria = categoriaRepository.findById(detalleProblemaDTO.getCategoriaId()).orElse(null);
            Prioridad prioridad = prioridadRepository.findById(detalleProblemaDTO.getPrioridadId()).orElse(null);
            Usuario usuario = usuarioRepository.findById(detalleProblemaDTO.getUsuarioId()).orElse(null);

            falloIncidencia.setActivoMaestro(activoMaestro);
            falloIncidencia.setCategoria(categoria);
            falloIncidencia.setPrioridad(prioridad);
            falloIncidencia.setUsuario(usuario);
            falloIncidencia.setSolucionado(false);
            // creando la incidencia
            falloIncidencia = detalleProblemaRepository.save(falloIncidencia);
            requestDTO.setFalloIncidencia(falloIncidencia);
            requestDTO.setStatusCode(201);
            requestDTO.setMessage("Se ha creado la incidencia correctamente");
            return requestDTO;
        } catch (Exception e) {
            requestDTO.setStatusCode(500);
            requestDTO.setMessage("Error al crear la incidencia: " + e.getMessage());
            return requestDTO;
        }
    }

    @Override
    public DetalleProblemaDTO actualizarIncidencia(Integer id, DetalleProblemaDTO detalleProblemaDTO) {
        DetalleProblemaDTO requestDTO = new DetalleProblemaDTO();
        try {
            Optional<DetalleProblema> falloItemOptional = detalleProblemaRepository.findById(id);
            if (falloItemOptional.isPresent()) {
                DetalleProblema falloItemUpdate = falloItemOptional.get();
                falloItemUpdate.setDescripcion(detalleProblemaDTO.getDescripcion());

                falloItemUpdate.setFechaRegistro(UtilsApp.formatearFechaInstant(detalleProblemaDTO.getFechaRegistro()));
                falloItemUpdate.setMedioReporte(detalleProblemaDTO.getMedioReporte());
                falloItemUpdate.setSolucion(detalleProblemaDTO.getSolucion());

                ActivoMaestro activoMaestro = activoMaestroRepository.findById(detalleProblemaDTO.getActivoMaestroId()).orElse(null);
                Categoria categoria = categoriaRepository.findById(detalleProblemaDTO.getCategoriaId()).orElse(null);
                Prioridad prioridad = prioridadRepository.findById(detalleProblemaDTO.getPrioridadId()).orElse(null);

                falloItemUpdate.setActivoMaestro(activoMaestro);
                falloItemUpdate.setCategoria(categoria);
                falloItemUpdate.setPrioridad(prioridad);
                falloItemUpdate.setSolucionado(detalleProblemaDTO.getSolucionado());

                DetalleProblema newFalloUpdate = detalleProblemaRepository.save(falloItemUpdate);
                requestDTO.setFalloIncidencia(newFalloUpdate);
                requestDTO.setStatusCode(201);
                requestDTO.setMessage("Incidencia: " + falloItemOptional.get().getCodigoProblema() + " actualizado con éxito");
            } else {
                requestDTO.setStatusCode(404);
                requestDTO.setMessage("Usuario: " + requestDTO.getCodigoProblema() + " no encontrado");
            }
        } catch (Exception e) {
            requestDTO.setStatusCode(500);
            requestDTO.setError("Ha ocurrido un error inesperado al actualizar la incidencia: " + e.getMessage());
        }
        return requestDTO;
    }

    @Override
    public DetalleProblemaDTO eliminarIncidencia(Integer id) {
        DetalleProblemaDTO requestDTO = new DetalleProblemaDTO();
        try {
            Optional<DetalleProblema> itemOptional = detalleProblemaRepository.findById(id);
            if (itemOptional.isPresent()) {
                detalleProblemaRepository.deleteById(id);
                requestDTO.setStatusCode(204);
                requestDTO.setMessage("Incidencia eliminada con éxito");
            } else {
                requestDTO.setStatusCode(404);
                requestDTO.setMessage("Incidencia no encontrada");
            }

        } catch (Exception e) {
            requestDTO.setStatusCode(500);
            requestDTO.setError("Ha ocurrido un error inesperado al eliminar la incidencia: " + e.getMessage());
        }
        return requestDTO;
    }

    @Override
    public DetalleProblemaDTO buscarIncidencia(String codigoProblema) {
        DetalleProblemaDTO requestDTO = new DetalleProblemaDTO();
        try {
            Optional<DetalleProblema> itemOptional = detalleProblemaRepository.findByCodigoProblema(codigoProblema);
            if (itemOptional.isPresent()) {
                DetalleProblema falloIncidencia = itemOptional.get();
                requestDTO.setCodigoProblema(falloIncidencia.getCodigoProblema());
                requestDTO.setDescripcion(falloIncidencia.getDescripcion());
                requestDTO.setFechaRegistro(falloIncidencia.getFechaRegistro().toString());
                requestDTO.setMedioReporte(falloIncidencia.getMedioReporte());
                requestDTO.setCodigoBien(falloIncidencia.getActivoMaestro().getCodigoBien());
                Optional<String> activoNombreOptional = detalleProblemaRepository.getNombreActivoPorCodigoProblema(falloIncidencia.getCodigoProblema());
                activoNombreOptional.ifPresent(requestDTO::setNombreActivo);
                requestDTO.setCategoria(falloIncidencia.getCategoria().getNombre());
                requestDTO.setPrioridad(falloIncidencia.getPrioridad().getNombre());
                requestDTO.setNombreUsuario(falloIncidencia.getUsuario().getNombre());
                requestDTO.setSolucionado(falloIncidencia.isSolucionado());
                requestDTO.setStatusCode(200);
                requestDTO.setMessage("incidencia buscada con exito");
            } else {
                requestDTO.setStatusCode(404);
                requestDTO.setMessage("Incidencia no encontrada");
            }

        } catch (Exception e) {
            requestDTO.setStatusCode(500);
            requestDTO.setError("Ha ocurrido un error al buscar la incidencia: " + e.getMessage());
        }
        return requestDTO;
    }

    @Override
    public Integer getTotalIncidenciasPorMes() {
        return detalleProblemaRepository.countIncidenciasByMonth();
    }

    @Override
    public Map<String, Object> getIncidenciasDosMonthComparacion() {
        List<Map<String, Object>> resultadosTable = detalleProblemaRepository.getIncidenciasComparacion();

        if (resultadosTable.size() < 2) {
            // si no estan los dos meses
            return Map.of("mesActual", 0, "mesAnterior", 0);
        }

        Map<String, Object> mesActual = resultadosTable.get(0);
        Map<String, Object> mesAnterior = resultadosTable.get(1);

        long totalMesActual = (long) mesActual.get("total");
        long totalMesAnterior = (long) mesAnterior.get("total");

        double porcentaje = (double) ((totalMesActual - totalMesAnterior) / totalMesAnterior) * 100;
        boolean incremento = porcentaje >= 0;

        return Map.of(
                "porcentaje", Math.abs(Math.round(porcentaje)),
                "esIncremento", incremento
        );
    }

    @Override
    public Integer getTotalIncidenciasPorDia() {
        return detalleProblemaRepository.countIncidenciasByDay();
    }

    @Override
    public Map<String, Object> getIncidenciasDosDayComparacion() {
        List<Map<String, Object>> resultadoTable = detalleProblemaRepository.getIncidenciasDiaComparacion();

        if (resultadoTable.size() < 2) {
            return Map.of("diaActual", 0, "diaAnterior", 0);
        }

        Map<String, Object> diaActual = resultadoTable.get(0);
        Map<String, Object> diaAnterior = resultadoTable.get(1);

        long totalDiaActual = (long) diaActual.get("total");
        long totalDiaAnterior = (long) diaAnterior.get("total");

        double porcentaje = getPorcentaje(totalDiaAnterior, totalDiaActual);

        log.info("total dia actual: " + totalDiaActual + " total dia anterior: " + totalDiaAnterior + " procentaje: " + porcentaje);
        boolean incremento = porcentaje >= 0;

        return Map.of(
                "porcentaje", Math.abs(Math.round(porcentaje)),
                "esIncremento", incremento
        );
    }

    private double getPorcentaje(long totalDiaAnterior, long totalDiaActual) {
        double porcentaje;
        if (totalDiaAnterior == 0) {
            // Si el día anterior tiene 0 problema
            if (totalDiaActual > 0) {
                porcentaje = 100.0; // Se considera un incremento del 100%
            } else {
                porcentaje = 0.0; // No hay cambios si ambos son 0
            }
        } else {
            // Si el día anterior tiene problema, calculamos el porcentaje normalmente
            porcentaje = ((double) (totalDiaActual - totalDiaAnterior) / totalDiaAnterior) * 100;
        }
        return porcentaje;
    }

    @Override
    public Integer getIncidenciasTotalesSolucionadas() {
        int totalIncidenciasSolucionadas;
        totalIncidenciasSolucionadas = detalleProblemaRepository.getDetalleProblemaSolucionados();
        return totalIncidenciasSolucionadas;
    }

    @Override
    public Integer getPromedioIncidenciasSolucionadas() {
        int promedioIncidenciasSolucionadas;
        promedioIncidenciasSolucionadas = detalleProblemaRepository.getPromedioDetalleProblemaResueltos();
        return promedioIncidenciasSolucionadas;
    }

    @Override
    public Map<String, Object> getEmpleadoCantidadMaxIncidencias() {
        List<Map<String, Object>> resultadoTabla = detalleProblemaRepository.getMaxEmpleadoDetalleProblema();

        Map<String, Object> empleado = resultadoTabla.getFirst();

        return Map.of(
                "nombre", empleado.get("nombre"),
                "cantidad", empleado.get("cantidad")
        );
    }

    @Override
    public Integer getPromedioIncidenciasEmpleado() {
        int promedio;
        promedio = detalleProblemaRepository.getPromedioMaxEmpleadoDetalleProblema();
        return promedio;
    }

    @Override
    public Integer getIncidenciasTotalesAyer() {
        int total;
        total = detalleProblemaRepository.getTotalDetalleProblemaAyer();
        return total;
    }

    @Override
    public Integer getPromedioIncidenciasAyerHoy() {
        int promedio;
        promedio = detalleProblemaRepository.getPromedioDetalleProbemaEntreAyerHoy();
        return promedio;
    }

    @Override
    public Map<String, Object> getTotalManenimientoHoyAyer() {
        List<Map<String, Object>> resultadoTable = detalleProblemaRepository.getTotalManenimientoHoyAyer();
        Map<String, Object> map = resultadoTable.getFirst();
        return Map.of(
                "totalHoy", map.get("totalHoy"),
                "totalAyer", map.get("totalAyer")
        );
    }

    @Override
    public Map<String, Object> getTotalAuditoriaHoyTotal() {
        List<Map<String, Object>> resultadoTable = detalleProblemaRepository.getTotalAuditoriaHoyTotal();
        Map<String, Object> map = resultadoTable.getFirst();
        return Map.of(
                "totalHoy", map.get("totalHoy"),
                "total", map.get("total")
        );
    }

    @Override
    public DetalleProblemaDTO getTableResults() {
        DetalleProblemaDTO requestDTO = new DetalleProblemaDTO();
        try {
            List<Object[]> resultTable = detalleProblemaRepository.getTableResults();
            List<DetalleProblemaDTO> listIncidenciaDTO = new ArrayList<>();
            if (!resultTable.isEmpty()) {
                for (Object[] objects : resultTable) {
                    DetalleProblemaDTO detalleProblemaDTO = new DetalleProblemaDTO();
                    detalleProblemaDTO.setStatusCode(200);
                    detalleProblemaDTO.setActivoMaestroId((Integer) objects[0]);
                    detalleProblemaDTO.setCodigoBien((String) objects[1]);
                    detalleProblemaDTO.setNombreActivo((String) objects[2]);
                    detalleProblemaDTO.setNombreEmpleado((String) objects[3]);
                    listIncidenciaDTO.add(detalleProblemaDTO);
                }
                requestDTO.setStatusCode(200);
                requestDTO.setMessage("Listado con éxito la tabla personalizada");
                requestDTO.setListFalloDetalleProblemaDTO(listIncidenciaDTO);
            }
            return requestDTO;
        } catch (Exception e) {
            requestDTO.setStatusCode(500);
            requestDTO.setError("Ha ocurrido un error inexperado al listar las problema: " + e.getMessage());
            return requestDTO;
        }
    }

    @Override
    public DetalleProblemaDTO getCantidadTotalIncidencaPorMes() {
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
                requestDTO.setListFalloDetalleProblemaDTO(listMesIncidenia);
            }
            return requestDTO;

        } catch (Exception e) {
            requestDTO.setStatusCode(500);
            requestDTO.setError("Ha ocurrido un error inexperado al obtener la cantidad de cada mes: " + e.getMessage());
            return requestDTO;
        }
    }

    @Override
    public DetalleProblemaDTO getTotalIncidencias() {
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
    public DetalleProblemaDTO getTotalIncidenciasDiasMes() {
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
                requestDTO.setListFalloDetalleProblemaDTO(listMesActualAnterior);
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
    public DetalleProblemaDTO getIncidenciasTotalMesActualAnterior() {
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
    public DetalleProblemaDTO getTotalIncidenciasFalloByPrioridad() {
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

}
