

(ns schemas.exercise1
  (:use clojure.pprint)
  (:require [schema.core :as s]))

(defn adiciona-paciente [pacientes paciente]
  (if-let [id (:id paciente)]
            (assoc pacientes id paciente)
            (throw (ex-info "O Paciente nao possui id" {:paciente paciente}))))

(defn imprime-relatorio-de-paciente [visitas paciente]
  (println "Visitas do paciente" paciente "são" (get visitas paciente)))

(defn adiciona-visita
  [visitas paciente novas-visitas]
  (if (contains? visitas paciente)
    (update visitas paciente concat novas-visitas)  ;alter
    (assoc visitas paciente novas-visitas)))

(defn testa-uso-de-pacientes []
  (let [sam {:id 15 :name "Sam"}
        jaime {:id 20, :name "Jaime"}
        paul {:id 25, :name "Paul" }

        ;variation with reduce
        pacientes (reduce adiciona-paciente {} [sam jaime paul])

        ;variation with shadowing
        visitas {}
        visitas (adiciona-visita visitas 15 ["01/01/2019"])
        visitas (adiciona-visita visitas 20 ["01/02/2019" "01/01/2020"])
        visitas (adiciona-visita visitas 15 ["01/03/2019"])]
    (pprint pacientes)
    (pprint visitas)


(pprint (s/validate Long 15))))


(s/set-fn-validation! true)

(s/defn teste-simples [x :- Long]
  (println x))


(teste-simples 30)
;(teste-simples "Jim") do not work because it was expecting a long

(s/defn imprime-relatorio-de-paciente
  [visitas paciente Long]
  (println "Visitas do paciente" paciente "são" (get visitas paciente)))

(testa-uso-de-pacientes )
