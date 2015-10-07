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

package org.scalastyle.file

import scala.util.matching.Regex

import org.scalastyle.ColumnError
import org.scalastyle.FileChecker
import org.scalastyle.Lines
import org.scalastyle.ScalastyleError

class RegexLineChecker extends FileChecker {
  val errorKey = "regex.line"
  private val DefaultRegEx = ""

  def verify(lines: Lines): List[ScalastyleError] = {
    var errorList: List[ColumnError] = Nil

    val regExpStr = getString("regex", DefaultRegEx)
    val regExp = new Regex(regExpStr)

    for ( (line, idx) <- lines.lines.zipWithIndex ){
      val allMatches = regExp.findAllIn(line.text)

      while (allMatches.hasNext) {

        allMatches.next()

        val matchedLine = idx

        errorList = ColumnError(matchedLine + 1, allMatches.start, List(regExpStr)) :: errorList
      }

    }

    errorList.reverse
  }

}
