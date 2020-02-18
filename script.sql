-- Should be in migration
ALTER TABLE customer ENABLE ROW LEVEL SECURITY;

CREATE POLICY customer_policy
  ON customer
USING (department = CURRENT_USER);
