/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.any23.validator.rule;

import org.apache.any23.validator.DOMDocument;
import org.apache.any23.validator.Rule;
import org.apache.any23.validator.RuleContext;
import org.apache.any23.validator.ValidationReport;
import org.apache.any23.validator.ValidationReportBuilder;
import org.w3c.dom.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * Checks whether the meta attribute name is used to contain a property.
 *
 * @see MetaNameMisuseFix
 *
 * @author Davide Palmisano (palmisano@fbk.eu)
 * @author Michele Mostarda (mostarda@fbk.eu)
 */
public class MetaNameMisuseRule implements Rule {

    public static final String ERRORED_META_NODES = "errored-meta-nodes";

    @Override
    public String getHRName() {
        return "meta-name-misuse-rule";
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean applyOn(DOMDocument document, @SuppressWarnings("rawtypes") RuleContext context,
            ValidationReportBuilder validationReportBuilder) {
        List<Node> metaNodes = document.getNodes("/HTML/HEAD/META");
        boolean foundIssue = false;
        final List<Node> wrongMetaNodes = new ArrayList<>();
        for (Node metaNode : metaNodes) {
            Node nameNode = metaNode.getAttributes().getNamedItem("name");
            if (nameNode != null && nameNode.getTextContent().contains(":")) {
                foundIssue = true;
                wrongMetaNodes.add(metaNode);
                validationReportBuilder.reportIssue(ValidationReport.IssueLevel.ERROR,
                        "Error detected in meta node: name property contains a prefixed value.", metaNode);
            }
        }
        context.putData(ERRORED_META_NODES, wrongMetaNodes);
        return foundIssue;
    }

}
