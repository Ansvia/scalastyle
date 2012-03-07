// Copyright (C) 2011-2012 the original author or authors.
// See the LICENCE.txt file distributed with this work for additional
// information regarding copyright ownership.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package org.segl.scalastyle.scalariform

import scalariform.parser.CompilationUnit
import _root_.scalariform.lexer.Tokens._
import _root_.scalariform.lexer.Token
import _root_.scalariform.parser._
import org.segl.scalastyle.ScalariformChecker
import org.segl.scalastyle._

class EqualsHashCodeChecker extends AbstractMethodChecker {
  val errorKey = "equals.hash.code"

  def matches(t: BaseClazz[AstNode]): Boolean = {
    val hc = t.subs.exists(matchFunDefOrDcl(_, isHashCode))
    val eq = t.subs.exists(matchFunDefOrDcl(_, isEqualsObject))

    (hc && !eq) || (!hc && eq)
  }

  private def isHashCode(t: FunDefOrDcl): Boolean = methodMatch("hashCode", noParameter() _)(t)
}
