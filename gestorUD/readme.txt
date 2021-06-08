Para la prueba de la aplicación si no hay una base de datos hay que seguir unos pasos:

- Para cargar la base de datos con datos:
  - En la clase iso3.pt.model.Test.java en la función main, esta comentado el apartado de INSERCIONES EN LA BASE DE DATOS, es necesario descomentar ese código y comentar el de FUNCIONES DE PRUEBA DE LA CLASE PtDAO que esta justo debajo.
  - Y en conf.hibernate.cfg.xml  hay que comentar la línea 15:
  <property name="hbm2ddl.auto">update</property>
    Y descomentar la que esta comentada la línea 14:
  <property name="hbm2ddl.auto">create</property>

  Nota: hemos decidido usar el atributo update, porque el create hace drop de las tablas de la base de datos, pero a la hora de hacer las inserciones usamos el create.

- Una vez están los datos cargados, se vuelve a dejar como estaba, el código de INSERCIONES EN LA BASE DE DATOS comentado, (esto realmente no importa mucho si no se va a ejecutar la clase Test, para comprobar que el DAO funciona correctamente), y es IMPORTANTE dejar el hibernate.cfg.xml como estaba, con el create comentaado y el update descomentado, asi:

<!-- <property name="hbm2ddl.auto">create</property> --> 
        <property name="hbm2ddl.auto">update</property>
