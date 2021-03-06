(ns jobtech-taxonomy-database.core
  (:gen-class)
  (:require [datomic.client.api :as d]
            [clojure.pprint :as pp]
            [jobtech-taxonomy-database.datomic-connection :as conn]
            [jobtech-taxonomy-database.converters.driving-licence-converter]
            [jobtech-taxonomy-database.converters.employment-duration-converter]
            [jobtech-taxonomy-database.converters.employment-type-converter]
            [jobtech-taxonomy-database.converters.geographic-places-converter]
            [jobtech-taxonomy-database.converters.language-converter]
            [jobtech-taxonomy-database.converters.language-level-converter]
            [jobtech-taxonomy-database.converters.occupation-converter]
            [jobtech-taxonomy-database.converters.skills-converter]
            [jobtech-taxonomy-database.converters.occupation-skill-relation-converter]
            [jobtech-taxonomy-database.converters.SNI-level-converter]
            [jobtech-taxonomy-database.converters.wage-type-converter]
            [jobtech-taxonomy-database.converters.worktime-extent-converter]
            [jobtech-taxonomy-database.converters.employment-duration-new-changes]
            [jobtech-taxonomy-database.converters.occupation-new-changes-converter]
            [jobtech-taxonomy-database.converters.skills-converter-new-changes]
            [jobtech-taxonomy-database.converters.occupation-skill-relation-new-changes]
            [jobtech-taxonomy-database.converters.geographic-places-new-changes]
            [jobtech-taxonomy-database.converters.worktime-extent-new-changes]
            [jobtech-taxonomy-database.converters.SUN-education-field-converter]
            [jobtech-taxonomy-database.converters.version-0]
            [jobtech-taxonomy-database.converters.version-1]
            [jobtech-taxonomy-database.converters.version-2]
            [jobtech-taxonomy-database.converters.employment-type-new-changes]
            [jobtech-taxonomy-database.converters.converter-util :as u]))

(def converters
  "Each logic section of the old taxonomy can be handled by a converter
  set consisting of a reader, converter, writer and their
  namespace. By making the converter immutable it becomes easier to
  test. Add new converter sets here."
  '(

    ;;NOTE THIS SCRIPT HAS RUN IN TWO DIFFERENT STEPS, FIRST from version 0 to 1. Then verion 2!!

    ;; {:namespace jobtech-taxonomy-database.converters.version-0}
    ;; {:namespace jobtech-taxonomy-database.converters.driving-licence-converter}
    ;; {:namespace jobtech-taxonomy-database.converters.employment-duration-converter}
    ;; {:namespace jobtech-taxonomy-database.converters.employment-type-converter}
    ;; {:namespace jobtech-taxonomy-database.converters.geographic-places-converter}
    ;; {:namespace jobtech-taxonomy-database.converters.language-converter}
    ;; {:namespace jobtech-taxonomy-database.converters.language-level-converter}
    ;; {:namespace jobtech-taxonomy-database.converters.occupation-converter}
    ;; {:namespace jobtech-taxonomy-database.converters.skills-converter}
    ;; {:namespace jobtech-taxonomy-database.converters.occupation-skill-relation-converter}
    ;; {:namespace jobtech-taxonomy-database.converters.SNI-level-converter}
    ;; {:namespace jobtech-taxonomy-database.converters.wage-type-converter}
    ;; {:namespace jobtech-taxonomy-database.converters.worktime-extent-converter}
    ;; {:namespace jobtech-taxonomy-database.converters.version-1}

    {:namespace jobtech-taxonomy-database.converters.employment-type-new-changes}
    {:namespace jobtech-taxonomy-database.converters.employment-duration-new-changes}
    {:namespace jobtech-taxonomy-database.converters.occupation-new-changes-converter}
    {:namespace jobtech-taxonomy-database.converters.skills-converter-new-changes}
    {:namespace jobtech-taxonomy-database.converters.occupation-skill-relation-new-changes}
    {:namespace jobtech-taxonomy-database.converters.geographic-places-new-changes}
    {:namespace jobtech-taxonomy-database.converters.worktime-extent-new-changes}
    {:namespace jobtech-taxonomy-database.converters.SUN-education-field-converter}
    {:namespace jobtech-taxonomy-database.converters.version-2}

    ))

(defn find-dupes []
  (run! (fn [x]
          (let [converted-data ((ns-resolve (get x :namespace) 'convert))]
            (u/find-duplicate-ids converted-data))
          )  converters)
  )

(defn -main
  []
  (conn/init-new-db)
  (println "**** read from old taxonomy db, convert, and write to datomic...")
  (set! *print-length* 100000)
  (with-open [w (clojure.java.io/writer "/tmp/latest-convert-for-debug.edn")]
    (binding [*out* w]
      (run! (fn [x]
              (println "**** Calling " (get x :namespace))
              (let [converted-data ((ns-resolve (get x :namespace) 'convert))]
                (pp/write converted-data)
                (d/transact (conn/get-conn) {:tx-data converted-data})))
            converters))))
;; (-main)
;Should we add a source attribute to the transaction? See https://docs.datomic.com/on-prem/best-practices.html#add-facts-about-transaction-entity
