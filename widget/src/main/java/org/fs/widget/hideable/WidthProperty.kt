/*
 * Google Message Widget Android Kotlin Copyright (C) 2018 Fatih, .
 *  
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.fs.widget.hideable

import android.util.Property
import android.widget.TextView

class WidthProperty: Property<TextView, Int>(Int::class.java, "width") {

  override fun get(view: TextView?): Int {
    view?.let { v ->
      return v.layoutParams.width
    }
    return 0
  }

  override fun set(view: TextView?, value: Int?) {
    view?.layoutParams?.width = value ?: 0
    view?.requestLayout()
  }
}