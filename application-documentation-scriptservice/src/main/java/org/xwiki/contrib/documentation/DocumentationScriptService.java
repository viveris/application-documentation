/*
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.xwiki.contrib.documentation;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import org.xwiki.bridge.DocumentAccessBridge;
import org.xwiki.component.annotation.Component;
import org.xwiki.script.service.ScriptService;

/**
 * This is the script service for the Documentation application.
 *
 * @version $Id$
 * @since 1.0
 */
@Component
@Singleton
@Named("documentation")
public class DocumentationScriptService implements ScriptService
{
    @Inject
    private SectionNumberingManager sectionNumberingManager;

    @Inject
    private SectionOrderingManager sectionOrderingManager;

    @Inject
    private DocumentAccessBridge documentAccessBridge;

    /**
     * @return the {@link SectionNumberingManager}
     */
    public SectionNumberingManager getNumberingManager()
    {
        return this.sectionNumberingManager;
    }

    /**
     * @return the {@link SectionOrderingManager}
     */
    public SectionOrderingManager getOrderingManager()
    {
        return this.sectionOrderingManager;
    }

    /**
     * @return wether the current document is a section
     * @throws DocumentationException if an error happens
     */
    public boolean isSection() throws DocumentationException
    {
        return sectionNumberingManager.isSection(documentAccessBridge.getCurrentDocumentReference());
    }

    /**
     * @return the full numbering of the current document
     * @throws DocumentationException if an error happens
     */
    public String getFullNumbering() throws DocumentationException
    {
        return sectionNumberingManager.getFullNumbering(documentAccessBridge.getCurrentDocumentReference());
    }

    /**
     * @throws DocumentationException if an error happens
     */
    public void computeSiblings() throws DocumentationException
    {
        sectionOrderingManager.computePreviousAndNextSections(documentAccessBridge.getCurrentDocumentReference());
    }
}
