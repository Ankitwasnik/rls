DROP policy if exists tenant_users_isolation_policy ON public.users;

CREATE POLICY tenant_users_isolation_policy ON public.users
	USING (tenant_id = current_setting('app.tenant_id', true)::integer);

ALTER TABLE public.users ENABLE ROW LEVEL SECURITY;
