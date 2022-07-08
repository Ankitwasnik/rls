DROP policy if exists tenant_users_isolation_policy ON public.users;

CREATE POLICY tenant_users_isolation_policy ON public.users
	USING (tenant_id::text = current_setting('app.tenant_id', true));

ALTER TABLE public.users ENABLE ROW LEVEL SECURITY;
