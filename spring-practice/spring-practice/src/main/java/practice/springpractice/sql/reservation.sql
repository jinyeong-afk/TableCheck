create table reservation(
                            sequence BIGINT DEFAULT NEXT VALUE FOR "PUBLIC"."SYSTEM_SEQUENCE_EB44E915_3D3D_4677_BFDC_2CEF922E0B6F" NOT NULL NULL_TO_DEFAULT SEQUENCE "PUBLIC"."SYSTEM_SEQUENCE_EB44E915_3D3D_4677_BFDC_2CEF922E0B6F",
                            store_name varchar(255),
                            id varchar(255),
                            reserve_time datetime,
                            menu_name varchar(255),
                            menu_num int,
                            seat varchar(255)
)

