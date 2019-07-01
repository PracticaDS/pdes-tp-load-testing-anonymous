[![Build Status](https://travis-ci.org/PracticaDS/pdes-tp-load-testing-anonymous.svg?branch=master)](https://travis-ci.org/PracticaDS/pdes-tp-load-testing-anonymous)

# Load testing :: Grupo Anonymous :: PDeS

* [Repositorio Principal](https://github.com/PracticaDS/pdes-tp-anonymous)
* [Repositorio E2E](https://github.com/PracticaDS/pdes-tp-e2e-anonymous)
* [Último Reporte](https://practicads.github.io/pdes-tp-load-testing-anonymous/)

## Installing & Running

Project uses [sbt plugin][sbtplugindoc] of [gatling][gatlingdoc].
It contains basic simulation from gatling quick start bundle.

[sbtplugindoc]: https://gatling.io/docs/current/extensions/sbt_plugin/
[gatlingdoc]: https://gatling.io/docs/current/advanced_tutorial/

### Run

All tests:

```sh
sbt "gatling:test"
```

Single test:

```sh
sbt "gatling:testOnly pdes.anonymous.GetUsersSimulation"
```

Report:

```sh
sbt "gatling:lastReport"
```
