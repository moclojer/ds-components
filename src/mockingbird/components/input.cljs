(ns mockingbird.components.input
  (:refer-clojure :exclude [class type])
  (:require
   [helix.dom :as d]
   [mockingbird.lib :refer-macros [defnc]]))

(def styles
  {:text {:mockingbird (str "shadow-sm bg-gray-50 focus:ring-mockingbird-main "
                        "focus:border-mockingbird-main block w-full sm:text-sm "
                        "border-gray-300 rounded-md")
          :login (str)}
   :checkbox {:default (str "")}
   :file {:default (str "")}
   :select {:default (str "")}})

(def label-style "block mb-2 text-sm font-medium text-gray-900")

(defnc input
  [{:keys [class type template on-load
           on-change placeholder label
           children]
    :or {template :mockingbird
         type :text
         on-load (fn [_])
         on-change (fn [_])
         placeholder "Type here..."}}]

  (d/div
   (when label
     (d/label
      {:class label-style}
      label))
   (d/input
    {:class (str (get-in styles [type template]) " " class)
     :type (name type)
     :on-load on-load
     :on-change on-change
     :placeholder placeholder})
   children))
