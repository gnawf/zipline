// Copyright 2017 Google Inc.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//      http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
//
////////////////////////////////////////////////////////////////////////////////

package com.google.crypto.tink.subtle;

/**
 * This class implements point arithmetic on the elliptic curve <a
 * href="https://cr.yp.to/ecdh/curve25519-20060209.pdf">Curve25519</a>.
 *
 * <p>This class only implements point arithmetic, if you want to use the ECDH Curve25519 function,
 * please checkout {@code com.google.crypto.tink.subtle.X25519}.
 *
 * <p>This implementation is based on <a
 * href="https://github.com/agl/curve25519-donna/blob/master/curve25519-donna.c">curve255-donna C
 * implementation</a>.
 */
final class Curve25519 {
  /**
   * Conditionally copies a reduced-form limb arrays {@code b} into {@code a} if {@code icopy} is 1,
   * but leave {@code a} unchanged if 'iswap' is 0. Runs in data-invariant time to avoid
   * side-channel attacks.
   *
   * <p>NOTE that this function requires that {@code icopy} be 1 or 0; other values give wrong
   * results. Also, the two limb arrays must be in reduced-coefficient, reduced-degree form: the
   * values in a[10..19] or b[10..19] aren't swapped, and all all values in a[0..9],b[0..9] must
   * have magnitude less than Integer.MAX_VALUE.
   */
  static void copyConditional(long[] a, long[] b, int icopy) {
    int copy = -icopy;
    for (int i = 0; i < Field25519.LIMB_CNT; i++) {
      int x = copy & (((int) a[i]) ^ ((int) b[i]));
      a[i] = ((int) a[i]) ^ x;
    }
  }
}
