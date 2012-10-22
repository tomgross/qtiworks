/* Copyright (c) 2012, University of Edinburgh.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * * Redistributions of source code must retain the above copyright notice, this
 *   list of conditions and the following disclaimer.
 *
 * * Redistributions in binary form must reproduce the above copyright notice, this
 *   list of conditions and the following disclaimer in the documentation and/or
 *   other materials provided with the distribution.
 *
 * * Neither the name of the University of Edinburgh nor the names of its
 *   contributors may be used to endorse or promote products derived from this
 *   software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 * ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 *
 * This software is derived from (and contains code from) QTItools and MathAssessEngine.
 * QTItools is (c) 2008, University of Southampton.
 * MathAssessEngine is (c) 2010, University of Edinburgh.
 */
package uk.ac.ed.ph.jqtiplus.node.expression.general;

import uk.ac.ed.ph.jqtiplus.node.expression.ExpressionParent;
import uk.ac.ed.ph.jqtiplus.node.shared.VariableDeclaration;
import uk.ac.ed.ph.jqtiplus.node.shared.VariableType;
import uk.ac.ed.ph.jqtiplus.resolution.ResolvedTestVariableReference;
import uk.ac.ed.ph.jqtiplus.running.ItemProcessingContext;
import uk.ac.ed.ph.jqtiplus.running.ProcessingContext;
import uk.ac.ed.ph.jqtiplus.types.Identifier;
import uk.ac.ed.ph.jqtiplus.validation.ValidationContext;
import uk.ac.ed.ph.jqtiplus.value.Value;
import uk.ac.ed.ph.jqtiplus.xperimental.ToRefactor;

/**
 * Implementation of <code>default</code>.
 *
 * @author Jiri Kajaba
 */
public final class Default extends LookupExpression {

    private static final long serialVersionUID = -1031669571748673912L;

    /** Name of this class in xml schema. */
    public static final String QTI_CLASS_NAME = "default";

    public Default(final ExpressionParent parent) {
        super(parent, QTI_CLASS_NAME);
    }

    //----------------------------------------------------------------------

    @Override
    protected void validateResolvedItemVariableReference(final ValidationContext context, final Identifier variableReferenceIdentifier, final VariableDeclaration resolvedDeclaration) {
        context.checkVariableType(this, resolvedDeclaration, VariableType.RESPONSE, VariableType.TEMPLATE);
    }

    @Override
    protected void validateResolvedTestVariableReference(final ValidationContext context, final Identifier variableReferenceIdentifier,
            final ResolvedTestVariableReference resolvedReference) {
        context.checkVariableType(this, resolvedReference.getVariableDeclaration(),
                VariableType.RESPONSE, VariableType.TEMPLATE);
    }

    //----------------------------------------------------------------------

    /** FIXME: Finish this for tests */
    @ToRefactor
    @Override
    protected Value evaluateValidSelf(final ProcessingContext context, final Value[] childValues, final int depth) {
        final Identifier referenceIdentifier = getIdentifier();
        final ItemProcessingContext itemProcessingContext = (ItemProcessingContext) context;
        return itemProcessingContext.computeDefaultValue(referenceIdentifier);
    }

//    @Override
//    protected Value evaluateInThisItem(final ItemProcessingContext itemContext, final Identifier itemVariableIdentifier) {
//        return itemContext.computeDefaultValue(itemVariableIdentifier);
//    }
//
//    @Override
//    protected Value evaluateInThisTest(final TestProcessingContext testContext, final Identifier testVariableIdentifier) {
//        /* Default don't get overridden in tests */
//        /* FIXME: Should we even be allowed to access test variables here? */
//        final VariableDeclaration variableDeclaration = testContext.getSubjectTest().getVariableDeclaration(testVariableIdentifier);
//        return variableDeclaration != null ? variableDeclaration.getDefaultValue().evaluate() : NullValue.INSTANCE;
//    }
//
//    @Override
//    protected Value evaluateInReferencedItem(final int depth, final AssessmentItemRefAttemptController itemRefController, final Identifier itemVariableIdentifier) {
//        return itemRefController.getItemController().computeDefaultValue(itemVariableIdentifier);
//    }
}
