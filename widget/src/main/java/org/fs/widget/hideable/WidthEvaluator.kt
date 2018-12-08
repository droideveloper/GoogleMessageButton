/*
 * Google Message Widget Android Kotlin Copyright (C) 2018 Fatih.
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

import android.animation.IntEvaluator
import android.view.View

class WidthEvaluator(private val view: View): IntEvaluator() {

  override fun evaluate(fraction: Float, startValue: Int?, endValue: Int?): Int {
    val f = super.evaluate(fraction, startValue, endValue)
    view.layoutParams.width = f
    view.requestLayout()
    return f
  }
}