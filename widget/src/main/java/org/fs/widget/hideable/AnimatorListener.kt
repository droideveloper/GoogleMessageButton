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

import android.animation.Animator

class AnimatorListener(
    private val start: (() -> Unit)? = null,
    private val cancel: (() -> Unit)? = null,
    private val end: (() -> Unit)? = null,
    private val repeat: (() -> Unit)? = null): Animator.AnimatorListener {

  override fun onAnimationRepeat(animation: Animator?) = repeat?.invoke() ?: Unit
  override fun onAnimationEnd(animation: Animator?) = end?.invoke() ?: Unit
  override fun onAnimationCancel(animation: Animator?) = cancel?.invoke() ?: Unit
  override fun onAnimationStart(animation: Animator?) = start?.invoke() ?: Unit
}