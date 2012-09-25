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
 * This software is derived from (and contains code from) QTITools and MathAssessEngine.
 * QTITools is (c) 2008, University of Southampton.
 * MathAssessEngine is (c) 2010, University of Edinburgh.
 */
package uk.ac.ed.ph.jqtiplus.notification;

import uk.ac.ed.ph.jqtiplus.attribute.Attribute;
import uk.ac.ed.ph.jqtiplus.node.QtiNode;
import uk.ac.ed.ph.jqtiplus.value.BaseType;
import uk.ac.ed.ph.jqtiplus.value.Cardinality;

import java.util.Arrays;

/**
 * Partial implementation of {@link NotificationFirer} that fills in the various
 * convenience methods.
 *
 * @author David McKain
 */
public abstract class AbstractNotificationFirer implements NotificationFirer {

    @Override
    public void fireValidationError(final QtiNode owner, final String message) {
        final ModelNotification notification = new ModelNotification(owner, null, NotificationType.MODEL_VALIDATION, NotificationLevel.ERROR, message);
        fireNotification(notification);
    }

    @Override
    public void fireAttributeValidationError(final Attribute<?> attribute, final String message) {
        final ModelNotification notification = new ModelNotification(attribute.getOwner(), attribute, NotificationType.MODEL_VALIDATION, NotificationLevel.ERROR, message);
        fireNotification(notification);
    }

    @Override
    public void fireAttributeValidationWarning(final Attribute<?> attribute, final String message) {
        final ModelNotification notification = new ModelNotification(attribute.getOwner(), attribute, NotificationType.MODEL_VALIDATION, NotificationLevel.WARNING, message);
        fireNotification(notification);
    }

    @Override
    public void fireBaseTypeValidationError(final QtiNode owner, final BaseType[] requiredBaseTypes, final BaseType[] actualBaseTypes) {
        final ModelNotification notification = new ModelNotification(owner, null, NotificationType.MODEL_VALIDATION, NotificationLevel.WARNING,
                "Base type validation error: expected " + Arrays.toString(requiredBaseTypes)
                + " but got " + Arrays.toString(actualBaseTypes));
        fireNotification(notification);
    }

    @Override
    public void fireCardinalityValidationError(final QtiNode owner, final Cardinality[] requiredCardinalities, final Cardinality[] actualCardinalities) {
        final ModelNotification notification = new ModelNotification(owner, null, NotificationType.MODEL_VALIDATION, NotificationLevel.WARNING,
                "Cardinality validation error: expected " + Arrays.toString(requiredCardinalities)
                + " but got " + Arrays.toString(actualCardinalities));
        fireNotification(notification);
    }

}
