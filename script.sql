-- Should be in migration
ALTER TABLE customer ENABLE ROW LEVEL SECURITY;

CREATE POLICY customer_policy
  ON customer
USING (first_name = CURRENT_USER);
