package com.angcar.io;

import com.angcar.model.*;
import com.angcar.util.DatosMedicionDia;
import com.angcar.util.Hora;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ReaderFiles {

    // Localización csv´s
    private static final String PATH_ZONAS = "src/main/resources/calidad_aire_zonas.csv";
    private static final String PATH_UBICA_ESTACIONES = "src/main/resources/calidad_aire_estaciones.csv";
    private static final String PATH_METEO = "src/main/resources/calidad_aire_datos_meteo_mes.csv";
    private static final String PATH_CONTAMINACION = "src/main/resources/calidad_aire_datos_mes.csv";

    private static final String PATH_MAGNITUDES_CONTAMINACION = "src/main/resources/magnitudes_contaminacion.csv";
    private static final String PATH_MAGNITUDES_METEO = "src/main/resources/magnitudes_meteorizacion.csv";


    public static List<Meteorizacion> readDataOfPathMeteorologia() {

        Path path = Paths.get(String.valueOf(PATH_METEO));

        if (Files.exists(path)) {
            try (Stream<String> stream = Files.lines(path, Charset.forName("Cp1252"))) {
                return (stream
                        .map(s -> s.split(";", -1)).skip(1)
                        .map(splitted -> {

                            String provincia = splitted[0];
                            String municipio = splitted[1];
                            String estacion = splitted[2];
                            String magnitud = splitted[3];
                            String punto_muestreo = splitted[4];
                            int ano = Integer.parseInt(splitted[5]);
                            int mes = Integer.parseInt(splitted[6]);
                            int dia = Integer.parseInt(splitted[7]);


                            Hora[] horas = new Hora[24];
                            NumberFormat format = NumberFormat.getInstance(Locale.FRANCE);



                            int actualSplitted = 8;
                            for (int i = 0; i < 24; i++){
                                horas[i] = new Hora(splitted[actualSplitted].replace(',','.')
                                        , splitted[actualSplitted + 1], splitted[3]);
                                actualSplitted +=2;
                            }

                            DatosMedicionDia dayHoras = new DatosMedicionDia(horas);

                            return new Meteorizacion(provincia, municipio, estacion, magnitud, punto_muestreo, ano, mes, dia, dayHoras);
                        })
                        .collect(Collectors.toList()));
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
                return null;// Optional.empty(); //TODO: Recordar también cambiar los nulls por los optional empty
            }
        } else {
            return null; //Optional.empty();
        }
    }

    //TODO: IMPLEMENTAR DE NUEVO OPTIONAL EN TODOS LOS LISTENRS
    public static List<Contaminacion> readDataOfPathContaminacion() {
        Path path = Paths.get(String.valueOf(PATH_CONTAMINACION));

        if (Files.exists(path)) {
            try (Stream<String> stream = Files.lines(path, Charset.forName("Cp1252"))) {
                return //Optional.of(
                        stream
                        .map(s -> s.split(";", -1)).skip(1)
                        .map(splitted -> {

                            String provincia = splitted[0];
                            String municipio = splitted[1];
                            String estacion = splitted[2];
                            String magnitud = splitted[3];
                            String punto_muestreo = splitted[4];
                            int ano = Integer.parseInt(splitted[5]);
                            int mes = Integer.parseInt(splitted[6]);
                            int dia = Integer.parseInt(splitted[7]);

                            Hora[] horas = new Hora[24];

                            int actualSplitted = 8;
                            for (int i = 0; i < 24; i++){
                                horas[i] = new Hora(splitted[actualSplitted].replace(',','.')
                                        , splitted[actualSplitted + 1], splitted[3]);
                                actualSplitted +=2;
                            }

                            DatosMedicionDia dayHoras = new DatosMedicionDia(horas);

                            return new Contaminacion(provincia, municipio, estacion, magnitud, punto_muestreo, ano, mes, dia, dayHoras);

                        })
                        .collect(Collectors.toList()); //)
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
                return null;//Optional.empty();
            }
        } else {
            return null;//Optional.empty();
        }
    }

    public static List<UbicacionEstaciones> readDataOfPathUbicacionEstaciones() {
        Path path = Paths.get(PATH_UBICA_ESTACIONES);

        if (Files.exists(path)) {
            try (Stream<String> stream = Files.lines(path, Charset.forName("Cp1252"))) {
                return stream
                        .map(s -> s.split(";", -1)).skip(1)
                        .map(splitted -> {
                            String estacion_codigo = splitted[0];
                            String zona_calidad_aire_descripcion = splitted[1];
                            String estacion_municipio = splitted[2];
                            String estacion_fecha_alta = splitted[3];
                            String estacion_tipo_area = splitted[4];
                            String estacion_tipo_estacion = splitted[5];
                            String estacion_subarea_rural = splitted[6];
                            String estacion_direccion_postal = splitted[7];
                            String estacion_coord_UTM_ETRS89_x = splitted[8];
                            String estacion_coord_UTM_ETRS89_y = splitted[9];
                            String estacion_coord_longitud = splitted[10];
                            String estacion_coord_latitud = splitted[11];
                            String estacion_altitud = splitted[12];
                            String estacion_analizador_NO = splitted[13];
                            String estacion_analizador_NO2 = splitted[14];
                            String estacion_analizador_PM10 = splitted[15];
                            String estacion_analizador_PM2_5 = splitted[16];
                            String estacion_analizador_O3 = splitted[17];
                            String estacion_analizador_TOL = splitted[18];
                            String estacion_analizador_BEN = splitted[19];
                            String estacion_analizador_XIL = splitted[20];
                            String estacion_analizador_CO = splitted[21];
                            String estacion_analizador_SO2 = splitted[22];
                            String estacion_analizador_HCT = splitted[23];
                            String estacion_analizador_HNM = splitted[24];


                            return new UbicacionEstaciones(
                                    estacion_codigo
                                    , zona_calidad_aire_descripcion
                                    , estacion_municipio
                                    , estacion_fecha_alta
                                    , estacion_tipo_area
                                    , estacion_tipo_estacion
                                    , estacion_subarea_rural
                                    , estacion_direccion_postal
                                    , estacion_coord_UTM_ETRS89_x
                                    , estacion_coord_UTM_ETRS89_y
                                    , estacion_coord_longitud
                                    , estacion_coord_latitud
                                    , estacion_altitud
                                    , estacion_analizador_NO
                                    , estacion_analizador_NO2
                                    , estacion_analizador_PM10
                                    , estacion_analizador_PM2_5
                                    , estacion_analizador_O3
                                    , estacion_analizador_TOL
                                    , estacion_analizador_BEN
                                    , estacion_analizador_XIL
                                    , estacion_analizador_CO
                                    , estacion_analizador_SO2
                                    , estacion_analizador_HCT
                                    , estacion_analizador_HNM
                            );
                        })
                        .collect(Collectors.toList());
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
                return null;
            }
        }
        else{
            return null;
        }

    }

    public static List<ZonasMunicipio> readDataOfPathZonasMunicipio() {

        Path path = Paths.get(PATH_ZONAS);

        if (Files.exists(path)) {
            try (Stream<String> stream = Files.lines(path, Charset.forName("Cp1252"))) {
                return stream
                        .map(s -> s.split(";", -1)).skip(1)
                        .map(splitted -> {

                            String zona_calidad_aire_codigo = splitted[0];
                            String zona_calidad_aire_descripcion = splitted[1];
                            String zona_calidad_aire_municipio = splitted[2];

                            return new ZonasMunicipio(zona_calidad_aire_codigo,
                                    zona_calidad_aire_descripcion, zona_calidad_aire_municipio);

                        })
                        .collect(Collectors.toList());
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
                return null;//Optional.empty();
            }
        } else {
            return null; //Optional.empty();
        }
    }

    public static List<MagnitudContaminacion> readDataOfPathMagnitudContaminacion() {

        Path path = Paths.get(PATH_MAGNITUDES_CONTAMINACION);

        if (Files.exists(path)) {
            try (Stream<String> stream = Files.lines(path, Charset.forName("Cp1252"))) {
                return (stream
                        .map(s -> s.split(";", -1)).skip(1)
                        .map(splitted -> {


                            int codigo_magnitud = Integer.parseInt(splitted[0]);
                            String descripcion_magnitud = splitted[1];
                            int codigo_tecnica_medida = Integer.parseInt(splitted[2]);
                            String descripcion_tecnica_medida = splitted[3];
                            String unidad = splitted[4];
                            String descripcion_unidad = splitted[5];

                            return new MagnitudContaminacion(codigo_magnitud,
                                    descripcion_magnitud, codigo_tecnica_medida,
                                    descripcion_tecnica_medida, unidad, descripcion_unidad);

                        })
                        .collect(Collectors.toList()));
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
                return null;//Optional.empty();
            }
        } else {
            return null;//Optional.empty();
        }
    }

    public static List<MagnitudMeteorizacion> readDataOfPathMagnitudMeteorizacion() {

        Path path = Paths.get(PATH_MAGNITUDES_METEO);
        List<MagnitudMeteorizacion> meteoList;

        if (Files.exists(path)) {
            try (Stream<String> stream = Files.lines(path, Charset.forName("Cp1252"))) {
                return (stream
                        .map(s -> s.split(";", -1)).skip(1)
                        .map(splitted -> {
                            int codigo_magnitud = Integer.parseInt(splitted[0]);
                            String descripcion_magnitud = splitted[1];
                            int codigo_tecnica_medida = Integer.parseInt(splitted[2]);
                            String unidad = splitted[3];
                            String descripcion_unidad = splitted[4];

                            return new MagnitudMeteorizacion(codigo_magnitud,
                                    descripcion_magnitud, codigo_tecnica_medida, unidad, descripcion_unidad);
                        })
                        .collect(Collectors.toList()));
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
                return null;
            }
        }
        else{
            return null;
        }
    }
}