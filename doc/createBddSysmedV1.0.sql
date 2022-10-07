CREATE TABLE public.paciente (
	id_pac serial NOT NULL,
	nombre_pac varchar(25) NOT NULL,
	apellido_paterno_pac varchar(25) NOT NULL,
	apellido_materno_pac varchar(25) NULL,
	identificacion_pac varchar(10) NOT NULL,
	telefono_celular_pac varchar(10) NOT NULL,
	direccion_pac varchar(300) NOT NULL,
	correo_pac varchar(50) NOT NULL,
	CONSTRAINT paciente_pk PRIMARY KEY (id_pac)
);
COMMENT ON TABLE public.paciente IS 'Tabla para almacenar los pacientes';

-- Column comments

COMMENT ON COLUMN public.paciente.id_pac IS 'Id del paciente';
COMMENT ON COLUMN public.paciente.nombre_pac IS 'Nombre del paciente';
COMMENT ON COLUMN public.paciente.apellido_paterno_pac IS 'Apellido paterno del paciente';
COMMENT ON COLUMN public.paciente.apellido_materno_pac IS 'Apellido materno del paciente';
COMMENT ON COLUMN public.paciente.identificacion_pac IS 'Identificación del paciente';
COMMENT ON COLUMN public.paciente.telefono_celular_pac IS 'Teléfono celular del paciente';
COMMENT ON COLUMN public.paciente.direccion_pac IS 'Dirección del paciente';
COMMENT ON COLUMN public.paciente.correo_pac IS 'Correo del paciente';

ALTER TABLE public.paciente ADD fecha_nacimiento_pac date NOT NULL;
COMMENT ON COLUMN public.paciente.fecha_nacimiento_pac IS 'Fecha de nacimiento';


CREATE TABLE public.historia_clinica (
	id_his_cli serial NOT NULL,
	fecha_apertura_hiscli date NOT NULL,
	numero_historia_clinica varchar(10) NOT NULL,
	id_pac int NOT NULL,
	CONSTRAINT historia_clinica_pk PRIMARY KEY (id_his_cli),
	CONSTRAINT historia_clinica_fk FOREIGN KEY (id_pac) REFERENCES public.paciente(id_pac)
);
COMMENT ON TABLE public.historia_clinica IS 'Historia Clínica del paciente';

-- Column comments

COMMENT ON COLUMN public.historia_clinica.id_his_cli IS 'Id historia clínica';
COMMENT ON COLUMN public.historia_clinica.fecha_apertura_hiscli IS 'Fecha de apertura historia clínica';
COMMENT ON COLUMN public.historia_clinica.numero_historia_clinica IS 'Número de historia clínica';
COMMENT ON COLUMN public.historia_clinica.id_pac IS 'Identificador de paciente';


CREATE TABLE public.tipo_paciente (
	id_tippac serial NOT NULL,
	nombre_tippac varchar(25) NOT NULL,
	descripcion_tippac varchar(300) NOT NULL,
	CONSTRAINT tipo_paciente_pk PRIMARY KEY (id_tippac)
);
COMMENT ON TABLE public.tipo_paciente IS 'Tabla para almacenar el tipo de paciente';

-- Column comments

COMMENT ON COLUMN public.tipo_paciente.id_tippac IS 'Id del tipo de paciente';
COMMENT ON COLUMN public.tipo_paciente.nombre_tippac IS 'Nombre del tipo de paciente';
COMMENT ON COLUMN public.tipo_paciente.descripcion_tippac IS 'Descripción del tipo de paciente';


ALTER TABLE public.paciente ADD id_tippac int NOT NULL;
COMMENT ON COLUMN public.paciente.id_tippac IS 'Identificador del tipo de paciente';
ALTER TABLE public.paciente ADD CONSTRAINT paciente_fk FOREIGN KEY (id_tippac) REFERENCES public.tipo_paciente(id_tippac);


ALTER TABLE public.paciente ADD foto_pac bytea NULL;
COMMENT ON COLUMN public.paciente.foto_pac IS 'Foto del paciente';
