/*
 * Copyright (C) 2011 Everit Kft. (http://www.everit.org)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.everit.blobstore.jdbc.test;

import javax.sql.XADataSource;

import org.apache.derby.jdbc.EmbeddedXADataSource;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.querydsl.sql.DerbyTemplates;
import com.querydsl.sql.SQLTemplates;

public class DerbyJdbcBlobstoreTest extends AbstractJdbcBlobstoreTest {

  private static EmbeddedXADataSource embeddedXADataSource;

  @AfterClass
  public static void afterClass() {
    embeddedXADataSource.setShutdownDatabase("shutdown");
    embeddedXADataSource = null;
  }

  @BeforeClass
  public static void beforeClass() {
    System.setProperty("derby.stream.error.file", "");

    embeddedXADataSource = new EmbeddedXADataSource();
    embeddedXADataSource.setCreateDatabase("create");

    embeddedXADataSource.setDatabaseName("memory:testDB");
    embeddedXADataSource.setConnectionAttributes("create=true");
    embeddedXADataSource.setUser("");
    embeddedXADataSource.setPassword("");
  }

  @Override
  protected SQLTemplates getSQLTemplates() {
    return new DerbyTemplates(true);
  }

  @Override
  protected XADataSource getXADataSource() {
    return embeddedXADataSource;
  }

  @Test
  @Ignore
  @Override
  public void testParallelBlobManipulationWithTwoTransactions() {
    super.testParallelBlobManipulationWithTwoTransactions();
  }

  @Override
  @Test
  @Ignore
  public void testVersionIsUpgradedDuringUpdate() {
    super.testVersionIsUpgradedDuringUpdate();
  }

}
