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

import android.support.design.widget.CoordinatorLayout
import android.support.v4.view.ViewCompat
import android.view.View

class GoogleMessageButtonBehaviour: CoordinatorLayout.Behavior<GoogleMessageButton>() {

  override fun onStartNestedScroll(c: CoordinatorLayout, view: GoogleMessageButton, dt: View, t: View, axes: Int, type: Int): Boolean = (axes and ViewCompat.SCROLL_AXIS_VERTICAL) != 0 && (view.visibility == View.VISIBLE)

  override fun onNestedPreScroll(c: CoordinatorLayout, view: GoogleMessageButton, t: View, dx: Int, dy: Int, co: IntArray, type: Int) = view.translate(dy)
}